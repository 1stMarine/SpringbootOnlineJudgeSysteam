package com.ckw.match.server.impl;

import com.ckw.common.utils.SnowflakeIdWorker;
import com.ckw.match.mapper.MatchMapper;
import com.ckw.match.pojo.Match;
import com.ckw.match.server.MatchService;
import com.ckw.match.task.DoScheduledController;
import com.ckw.question.mapper.QuestionMapper;
import com.ckw.question.mapper.RecordMapper;
import com.ckw.question.pojo.MatchResult;
import com.ckw.question.pojo.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private DoScheduledController doScheduledController;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addMatch(Match match) {
        match.setId(SnowflakeIdWorker.snowFlow.nextId());
        match.setState("未开始");
        match.setCreateTime(new SimpleDateFormat("yyyy-MM-dd- HH:mm").format(System.currentTimeMillis()));
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
    public boolean addMatchQuestion(List<String> qid,long mid) {
        return matchMapper.addMatchQuestion(qid,mid);
    }

    /**
     * @param mid
     * @return
     */
    @Override
    public boolean deleteMatchQuestion(String mid) {
        return matchMapper.deleteMatchQuestion(mid);
    }

    @Override
    @Transactional
    public Match getMatchDetail(long mid) {
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
        matchDetail.setQuestionIds(matchQuestionIds);

        return matchDetail;
    }

    @Transactional
    @Override
    public boolean participateMatch(long uid,long mid) {
        matchMapper.addParticipateCount(mid);
        return matchMapper.addMatchUser(uid,mid);
    }

    @Override
    public List<String> getUserMatch(long uid) {
        return matchMapper.getUserMatch(uid);
    }

    @Override
    public List<MatchResult> getMatchResult(long mid) {
        return recordMapper.getMatchResult(mid);
    }

    /**
     * 删除一个竞赛
     *
     * @param mid
     * @return
     */
    @Override
    public boolean deleteMatch(long mid) {
        return matchMapper.deleteMatch(mid);
    }

    /**
     * 检查这个用户有没有参加这个竞赛
     *
     * @param uid
     * @param mid
     * @return
     */
    @Override
    public boolean checkUserParticipateMatch(long uid, long mid) {
        return matchMapper.checkUserParticipateMatch(uid, mid) >= 1;
    }

    /**
     * @param mid
     * @return
     */
    @Override
    public List<String> getMatchUserImgUrl(long mid) {
        return matchMapper.getMatchUserImgUrl(mid);
    }

    /**
     * @param match
     * @return
     */
    @Override
    @Transactional
    public boolean updateMatchInfo(Match match) {
        log.info(match.toString());
        boolean done = false;
        done = matchMapper.deleteMatchQuestion(String.valueOf(match.getId()));
        done = matchMapper.addMatchQuestion(match.getQuestionIds(), match.getId());
        done = matchMapper.updateMatch(match);
        return done;
    }


}
