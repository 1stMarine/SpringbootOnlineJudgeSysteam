package com.ckw.question.server.impl;



import com.ckw.common.netty.NioWebSocketHandler;
import com.ckw.common.pojo.State;
import com.ckw.common.utils.DateUtils;
import com.ckw.common.utils.SnowflakeIdWorker;
import com.ckw.question.mapper.RecordMapper;
import com.ckw.judger.pojo.*;
import com.ckw.judger.judger.Judger;
import com.ckw.judger.utils.ContainerUtils;
import com.ckw.question.mapper.QuestionMapper;


import com.ckw.question.pojo.MatchResult;
import com.ckw.question.pojo.TestSamples;
import com.ckw.question.server.JudgeServer;
import com.ckw.user.mapper.UserMapper;
import com.ckw.user.mapper.UserRankMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class JudgeServerImpl implements JudgeServer {
    @Autowired
    public ContainerUtils containerUtils;

    @Autowired
    public RecordMapper recordMapper;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public QuestionMapper questionMapper;

    @Autowired
    private UserRankMapper userRankMapper;

    @Override
    public TestResult matchJudge(TestPack testPack) {
        // TODO 判断比赛开始没 健壮性
        return doJudge(testPack);
    }

    @Override
    public TestResult normalJudge(TestPack testPack) {
        return doJudge(testPack);
    }

    @Override
    public TestResult doJudge(TestPack testPack) {
        System.out.println(NioWebSocketHandler.userWebSocketId);
        NioWebSocketHandler.textWebSocketFrameHandler(
                NioWebSocketHandler.userWebSocketId.get(String.valueOf(testPack.getUid())),
                "1","初始化...","初始化完成"
        );
//        创建时间
        Long time = System.currentTimeMillis();
//        拉取测试样例
        List<TestSample> testSamples = getTestSamples(testPack.getQid());

        testPack.setTestSampleList(testSamples);
//        时间内空间
        testPack.setMemoryLimit(questionMapper.getQuestionMemoryLimit(testPack.getQid()));
        testPack.setTimeLimit(questionMapper.getQuestionTimeLimit(testPack.getQid()) * 1000);
//          对测试对象进行设置
        testPack.setSubmitTime(time);
        testPack.setSubmitTimeFormat(
                new SimpleDateFormat("yyyy-MM-dd- HH:mm:ss").format(time)
        );

//        测试结果
        TestResult testResult = new TestResult();
        try {
            //          新建判题机运行
            Judger judger = new Judger(testPack);
            testResult = judger.run();
        }finally {
            //        删除判题机（异步）
            containerUtils.deleteContainer(testPack.getContainerId());
        }
//        保存记录

        return testResult;

    }

    /**
     * 编译后相关设置
     * 如 用户解题数量增加
     * 题目通过梳理增加
     * 保存编译记录等
     * @param testResult
     * @param testPack
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean setting(TestResult testResult,TestPack testPack ){
        boolean flag = false;
//        报存编译记录
        flag = saveSubmitRecord(new SubmitRecord(
                SnowflakeIdWorker.snowFlow.nextId(),
                testResult.getUid(),
                testResult.getQid(),
                testPack.getQuestionName(),
                testResult.getTime(),
                testResult.getMemory(),
                testPack.getLanguage(),
                testPack.getCode(),
                testPack.getSubmitTimeFormat(),
                testResult.getTitle(),
                testResult.getMessage(),
                testResult.getTestSample() == null ? "{}" :
                        String.format("{\"input\":\"%s\",\"output\":\"%s\",\"userOutput\":\"%s\"}",
                            testResult.getTestSample().getInput(),
                                testResult.getTestSample().getOutput(),
                                testResult.getTestSample().getUserOutput()
                        ),
                testPack.getUserName()
        ));
//        更改用户解题数量
        if(testResult.isPass()){
//            增加经验值、等级
            updateExperienceAndLevel(testPack.getQid(), testPack.getUid());
//            确认这到底没有解锁过
            if(recordMapper.queryUserResolveQuestion(testPack.getUid(), testPack.getQid()) == 0){
//                增加结题数量
                String difficulty = questionMapper.queryQuestion(testPack.getQid()).getDifficulty();
                log.info(difficulty+"====="+testPack.getUid());
                userMapper.changeUserResolve(difficulty, testPack.getUid());
//                添加到已解决题目列表
                recordMapper.addUserResolve(testPack.getUid(), testPack.getQid());

                //            增加日、月解题数量,未解决的需要增加
                String date = (DateUtils.getYear() + "-" + DateUtils.getMonth() + "-" + DateUtils.getDay());
                int i = userRankMapper.checkDaySubmit(date, testPack.getUid());
//            有就修改数量、没有就新增
                if(i != 0){
                    userRankMapper.addDayUserSubmit(date, testPack.getUid());
                }else{
                    userRankMapper.insertDaySubmit(date, testPack.getUid());
                }

                i = userRankMapper.checkMonthSubmit(DateUtils.getYear(),DateUtils.getMonth(), testPack.getUid());
                if(i != 0){
                    userRankMapper.addMonthUserSubmit(DateUtils.getYear(),DateUtils.getMonth(), testPack.getUid());
                }else{
                    userRankMapper.insertMonthSubmit(DateUtils.getYear(),DateUtils.getMonth(), testPack.getUid());
                }
            }

        }

        return flag;
    }

    /**
     * 必要设置，不参与事务回滚
     * @param testResult
     * @param testPack
     * @return
     */
    public boolean mustSetting(TestResult testResult,TestPack testPack ){
        //更改题目总尝试\总通过
        boolean b = questionMapper.addTotalCount(
                testResult.isPass() ? 1 : 0, 1, testPack.getQid()
        );

        return b;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean settingForMatch(TestResult testResult,TestPack testPack){
        boolean flag = false;
        SubmitRecord submitRecord = new SubmitRecord(
                SnowflakeIdWorker.snowFlow.nextId(),
                testResult.getUid(),
                testResult.getQid(),
                testPack.getQuestionName(),
                testResult.getTime(),
                testResult.getMemory(),
                testPack.getLanguage(),
                testPack.getCode(),
                testPack.getSubmitTimeFormat(),
                testResult.getTitle(),
                testResult.getMessage(),
                testResult.getTestSample() == null ? "{}" :
                        String.format("{\"input\":\"%s\",\"output\":\"%s\",\"userOutput\":\"%s\"}",
                                testResult.getTestSample().getInput(),
                                testResult.getTestSample().getOutput(),
                                testResult.getTestSample().getUserOutput()
                        ),
                testPack.getUserName());
//        记录比赛时提交记录
        flag = recordMapper.addMatchRecord(submitRecord,testPack.getMid());
//        普通的编译记录也要保存
        flag = saveSubmitRecord(submitRecord);


//        更新结果
        MatchResult userMatchResult = recordMapper.getUserMatchResult(testPack.getMid(), testPack.getUid());
        JSONObject jsonObject = new JSONObject(userMatchResult.getResults());
        log.info("{}",testResult);
        if(State.RECORD_AC_MARK.equals(jsonObject.get(String.valueOf(testPack.getQid())))){
//            已经通过了，就不用进行接下来的操作
            return flag;
        }
        jsonObject.put(String.valueOf(testResult.getQid()),testResult.getTitle());
        userMatchResult.setResults(jsonObject.toString());
//        通过加分
        if(testResult.isPass()){
            userMatchResult.setTotalScore(userMatchResult.getTotalScore()+1);
        }
        log.info("{}",userMatchResult);
        recordMapper.updateUserMatchResult(userMatchResult);
//        没通过减分
        return flag;
    }

    @Override
    public void updateExperienceAndLevel(long qid, long uid) {
        String questionDifficulty = questionMapper.getQuestionDifficulty(qid);
        int experience = 0;
        switch (questionDifficulty){
            case "简单":
                experience = 100;
                break;
            case "中等":
                experience = 200;
                break;
            case "困难":
                experience = 300;
                break;
            case "噩梦":
                experience = 500;
                break;
            default:
                experience = 0;
        }
//        增加经验
        userMapper.addExperience(experience,uid);
//        经验溢出 ： 加等级
        if(userMapper.getExperience(uid) > userMapper.getLevel(uid) * 1000){
            userMapper.addLevel(uid);
        }
    }

    /**
     * 得到问题 并 将测试用例格式化为数组
     * @param id 题目id
     * @return 测试用例集合
     */
    @Override
    public List<TestSample> getTestSamples(long id) {
        TestSamples testSamples = questionMapper.getTestSample(id);
//        json解码
        JSONArray inputs = new JSONArray();
        JSONArray outputs = new JSONArray();
        if(testSamples != null){
            inputs = new JSONArray(testSamples.getSampleInput());
            outputs = new JSONArray(testSamples.getSampleOutput());
        }

        List<TestSample> testSample = new ArrayList<>();
//        遍历组装
        for (int i = 0; i < inputs.length(); i++) {
            testSample.add(new TestSample(
                    id,
                    inputs.get(i).toString(),
                    outputs.get(i).toString(),
                    "",
                    false
            ));
        }

        return testSample;
    }

    @Override
    public boolean saveSubmitRecord(SubmitRecord submitRecord) {
        return recordMapper.addRecord(submitRecord);
    }


}
