package com.ckw.match.server;

import com.ckw.match.pojo.Match;

import java.util.List;

public interface MatchServer {

    public boolean addMatch(Match match);

    List<Match> getMatchList();

    boolean addMatchQuestion(List<String> qid,int mid);

    Match getMatchDetail(int mid);

    boolean participateMatch(int mid,int uid);

    List<String> getUserMatch(int uid);
}
