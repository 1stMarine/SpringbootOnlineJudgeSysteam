package com.ckw.match.mapper;

import com.ckw.match.pojo.Match;
import com.ckw.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 比赛相关的sql操作
 * @author Echo
 */
@Mapper
@Repository
public interface MatchMapper{
    /**
     * 添加一个竞赛
     * @param match
     * @return
     */
    boolean addMatch(Match match);

    /**
     * 得到别赛列表
     * @return
     */
    List<Match> getMatchList();

    /**
     * 得到没开始或者没结束的比赛
     * @return
     */
    List<Match> getStartOrEndMatchs();

    /**
     * 添加比赛id
     * @param list
     * @param mid
     * @return
     */
    boolean addMatchQuestion(@Param("list") List<String> list, @Param("mid") long mid);

    boolean deleteMatchQuestion(String mid);

    /**
     * 得到比赛的详细信息
     * @param id
     * @return
     */
    Match getMatchDetail(long id);

    /**
     * 得到比赛的题目id
     * @param mid
     * @return
     */
    List<String> getMatchQuestionIds(long mid);

    /**
     * 增加参赛用户
     * @param uid
     * @param mid
     * @return
     */
    boolean addMatchUser(long uid,long mid);

    /**
     * 得到用户所参加的比赛
     * @param uid
     * @return
     */
    List<String> getUserMatch(long uid);

    /**
     * 将比赛设置为开始
     * @param mid
     * @return
     */
    boolean startMatch(long mid);

    /**
     * 比赛设置为结束
     * @param mid
     * @return
     */
    boolean endMatch(long mid);

    /**
     * 查询用户是否参加了这个比赛~~
     * @param uid
     * @param mid
     * @return
     */
    int checkUserParticipateMatch(long uid,long mid);
    /**
     * 得到参加这个比赛的用户~~
     * @param mid
     * @return
     */
    List<Long> getMatchUser(long mid);

    /**
     * 得带参加这个比赛的用户（对象形式）~~
     * @param mid
     * @return
     */
    List<User> getMatchUserObj(long mid);

    /**
     * 删除竞赛~~
     * @param mid
     * @return
     */
    boolean deleteMatch(long mid);
    /**
     * 增加参赛人数
     */
    boolean addParticipateCount(long mid);

    /**
     * 得到三个参加竞赛的用户头像
     * @param mid
     * @return
     */
    List<String> getMatchUserImgUrl(long mid);


    boolean updateMatch(Match match);
}
