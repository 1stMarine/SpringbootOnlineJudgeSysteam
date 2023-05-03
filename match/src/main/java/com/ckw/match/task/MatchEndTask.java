package com.ckw.match.task;

import com.ckw.common.utils.RedisUtils;
import com.ckw.judger.pojo.TestResult;
import com.ckw.match.mapper.MatchMapper;
import com.ckw.match.pojo.Match;
import com.ckw.match.pojo.UserMatchingInfo;
import com.ckw.question.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component

@Scope("prototype")
public class MatchEndTask implements Runnable {



    public Match match;
    @Autowired
    public MatchMapper matchMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private RedisUtils redisUtils;
    @Override
    public void run() {
//        改变竞赛信息
        matchMapper.endMatch(match.getId());
////        记录编译记录
//        List<Integer> matchUser = matchMapper.getMatchUser(match.getId());
////        每个用户
//        for (Integer uid : matchUser) {
//            UserMatchingInfo userMatchingInfo = (UserMatchingInfo) redisUtils.getObject(uid + "@" + match.getId());
//            Map<String, List<TestResult>> testResultMap = userMatchingInfo.getTestResultMap();
////            每道题
//            for (String qid : testResultMap.keySet()) {
//                List<TestResult> testResults = testResultMap.get(qid);
////                每个此时结果
//                for (TestResult testResult : testResults) {
//                    System.out.println(testResult);
//                }
//            }
//        }

        System.out.println(match.getMatchName() + "竞赛结束");
    }
}
