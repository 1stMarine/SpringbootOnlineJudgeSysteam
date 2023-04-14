package com.ckw.match.task;

import com.ckw.common.utils.RedisUtils;
import com.ckw.common.utils.SnowflakeIdWorker;
import com.ckw.match.mapper.MatchMapper;
import com.ckw.match.pojo.Match;
import com.ckw.question.mapper.RecordMapper;
import com.ckw.question.pojo.MatchResult;
import com.ckw.user.pojo.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 开始竞赛需要做的内容
 */
@Component

@Scope("prototype")
public class MatchStartTask implements Runnable {

    public Match match;


    @Autowired
    public MatchMapper matchMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void run() {
////  得到竞赛的题目id
//        List<String> matchQuestionIds = matchMapper.getMatchQuestionIds(match.getId());
//
////        得到参加竞赛用户id
//        List<Integer> matchUserId = matchMapper.getMatchUser(match.getId());
////        key : 用户id + 竞赛id , 每一个用户一个对象，里面存放每一道题目的提交信息
//        for (Integer userId : matchUserId) {
//            redisUtils.insertObject(userId+"@"+ match.getId(),new UserMatchingInfo(userId+"@"+ match.getId(),matchQuestionIds));
//        }

//改变竞赛状态
        matchMapper.startMatch(match.getId());
        System.out.println(match.getMatchName() +  " 竞赛开始 ");
//        竞赛结果添加
        List<Integer> matchQuestionIds = matchMapper.getMatchQuestionIds(match.getId());
        JSONObject jsonObject = new JSONObject();
        for (Integer qid : matchQuestionIds) {
            jsonObject.put(String.valueOf(qid),"Empty");
        }
        List<User> matchUserObj = matchMapper.getMatchUserObj(match.getId());
        for (User user : matchUserObj) {
            MatchResult matchResult = new MatchResult(
                    SnowflakeIdWorker.nextId(),
                    user.getId(),
                    user.getNickName(),
                    match.getId(),
                    jsonObject.toString(),
                    0 );
            recordMapper.addUserMatchResult(matchResult);
        }
//        开始竞赛任务
    }
}
