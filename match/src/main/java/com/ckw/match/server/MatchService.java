package com.ckw.match.server;

import com.ckw.match.pojo.Match;
import com.ckw.question.pojo.MatchResult;

import java.util.List;

public interface MatchService {

    public boolean addMatch(Match match);

    List<Match> getMatchList();

    boolean addMatchQuestion(List<String> qid,long mid);

    boolean deleteMatchQuestion(String mid);

    Match getMatchDetail(long mid);

    boolean participateMatch(long mid,long uid);

    List<String> getUserMatch(long uid);

    List<MatchResult> getMatchResult(long mid);

    /**
     * 删除一个竞赛
     * @param mid
     * @return
     */
    boolean deleteMatch(long mid);

    /**
     * 检查这个用户有没有参加这个竞赛
     * @param uid
     * @param mid
     * @return
     */
    boolean checkUserParticipateMatch(long uid,long mid);

    /**
     * 得到参加这个竞赛的用户头像(前三个)
     * @param mid
     * @return
     */
    List<String> getMatchUserImgUrl(long mid);

    boolean updateMatchInfo(Match match);
}
