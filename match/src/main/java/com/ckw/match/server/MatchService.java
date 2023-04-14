package com.ckw.match.server;

import com.ckw.match.pojo.Match;
import com.ckw.question.pojo.MatchResult;

import java.util.List;

public interface MatchService {

    public boolean addMatch(Match match);

    List<Match> getMatchList();

    boolean addMatchQuestion(List<String> qid,int mid);

    Match getMatchDetail(int mid);

    boolean participateMatch(int mid,int uid);

    List<String> getUserMatch(int uid);

    List<MatchResult> getMatchResult(int mid);

    /**
     * 删除一个竞赛
     * @param mid
     * @return
     */
    boolean deleteMatch(int mid);
}
