package com.ckw.match.task;

import com.ckw.match.mapper.MatchMapper;
import com.ckw.match.pojo.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MatchEndTask implements Runnable {



    public Match match;
    @Autowired
    public MatchMapper matchMapper;

    @Override
    public void run() {
        matchMapper.endMatch(match.getId());
        System.out.println(match.getMatchName() + "竞赛结束");
    }
}
