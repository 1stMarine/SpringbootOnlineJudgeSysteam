package com.ckw.match.mapper;

import com.ckw.match.pojo.Match;
import com.ckw.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MatchMapper{
    boolean addMatch(Match match);

    List<Match> getMatchList();

    List<Match> getStartOrEndMatchs();

    boolean addMatchQuestion(@Param("list") List<String> list, @Param("mid") int mid);


    Match getMatchDetail(int id);

    List<String> getMatchQuestionIds(int mid);

    boolean addMatchUser(int uid,int mid);

    List<String> getUserMatch(int uid);

    boolean startMatch(int mid);

    boolean endMatch(int mid);

    List<Integer> getMatchUser(int mid);

    List<User> getMatchUserObj(int mid);

    boolean deleteMatch(int mid);

}
