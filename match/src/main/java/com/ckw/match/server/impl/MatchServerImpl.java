package com.ckw.match.server.impl;

import com.ckw.common.utils.SnowflakeIdWorker;
import com.ckw.match.mapper.MatchMapper;
import com.ckw.match.pojo.Match;
import com.ckw.match.server.MatchServer;
import com.ckw.match.task.DoScheduledController;
import com.ckw.question.mapper.QuestionMapper;
import com.ckw.question.pojo.Question;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MatchServerImpl implements MatchServer {

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private DoScheduledController doScheduledController;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addMatch(Match match) {
        match.setId(SnowflakeIdWorker.nextId());
        match.setState("未开始");
        matchMapper.addMatch(match);
        addMatchQuestion(match.getQuestionIds(),match.getId());
//        刷新任务列表
        doScheduledController.startTask();

        return true;
    }

    @Override
    public List<Match> getMatchList() {
        return matchMapper.getMatchList();
    }

    @Override
    public boolean addMatchQuestion(List<String> qid,int mid) {
        return matchMapper.addMatchQuestion(qid,mid);
    }

    @Override
    @Transactional
    public Match getMatchDetail(int mid) {
//        拿到比赛题目的id
        List<String> matchQuestionIds = matchMapper.getMatchQuestionIds(mid);
//        根据得到id查题目内容
        List<Question> matchQuestion = new ArrayList<>();
        if(matchQuestionIds.size() != 0){
            matchQuestion = questionMapper.getMatchQuestion(matchQuestionIds);
        }
//        查询比赛
        Match matchDetail = matchMapper.getMatchDetail(mid);
        matchDetail.setQuestions(matchQuestion);
        return matchDetail;
    }

    @Override
    public boolean participateMatch( int uid,int mid) {
        return matchMapper.addMatchUser(uid,mid);
    }

    @Override
    public List<String> getUserMatch(int uid) {
        return matchMapper.getUserMatch(uid);
    }
}
