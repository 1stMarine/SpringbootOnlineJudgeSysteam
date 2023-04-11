package com.ckw.match.task;

import com.ckw.common.utils.RedisUtils;
import com.ckw.match.mapper.MatchMapper;
import com.ckw.match.pojo.Match;
import com.ckw.match.pojo.UserMatchingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class MatchStartTask implements Runnable {

    public Match match;


    @Autowired
    public MatchMapper matchMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void run() {

        List<String> matchQuestionIds = matchMapper.getMatchQuestionIds(match.getId());
        List<Integer> matchUserId = matchMapper.getMatchUser(match.getId());

        for (Integer userId : matchUserId) {
            redisUtils.insertObject(userId+"@"+ match.getId(),new UserMatchingInfo(userId+"@"+ match.getId(),matchQuestionIds));
        }


        matchMapper.startMatch(match.getId());
        System.out.println(match.getMatchName() +  " 竞赛开始 ");
//        开始竞赛任务
    }
}
