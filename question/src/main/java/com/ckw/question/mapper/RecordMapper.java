package com.ckw.question.mapper;

import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.question.pojo.MatchResult;
import com.ckw.question.pojo.UserResolve;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {

    /**
     * 增加记录
     * @param submitRecord
     * @return
     */
    boolean addRecord(SubmitRecord submitRecord);

    /**
     * 得到某个用户在某个题目的提交记录
     * @param uid
     * @param qid
     * @param page
     * @return
     */
    List<SubmitRecord> getSubmitRecordList(long uid,long qid,long page);
    /**
     * 得到所有提交记录
     * @return
     */
    List<SubmitRecord> getAllSubmitRecordList();
    /**
     * 得到某个题目的提交记录
     * @param qid
     * @param page
     * @return
     */
    List<SubmitRecord> getSubmitRecordListWithQid(long qid,long page);
    /**
     * 得到某个用户的提交记录
     * @param uid
     * @param page
     * @return
     */
    List<SubmitRecord> getSubmitRecordListWithUid(long uid,long page);
    /**
     * 得到提价记录的代码
     * @return
     */
    List<String> getSubmitRecordCode();
    /**
     * 增加比赛提交记录
     * @param submitRecord
     * @param mid
     * @return
     */
    boolean addMatchRecord(SubmitRecord submitRecord,long mid);
    /**
     * 得到通过的提交记录
     * @param mid
     * @return
     */
    List<SubmitRecord> getAllMatchSubmitRecordOnlyAC(long mid);

    /**
     * 得到所有的记录，没有限制
     * @param mid
     * @return
     */
    List<SubmitRecord> getAllMatchSubmitRecordNoLimit(long mid);

//    拿到比赛分数记录
    MatchResult getUserMatchResult(long mid,long uid);
//    更新比赛分数
    boolean updateUserMatchResult(MatchResult matchResult);
//    新增用户比赛分数
    boolean addUserMatchResult(MatchResult matchResult);
//    得到竞赛的所有结果
    List<MatchResult> getMatchResult(long mid);

//    得到用户解决过的问题
    List<Long> getUserResolveRecord(long uid);

    /**
     * 添加用户解决的问题
     * @param uid
     * @param qid
     * @return
     */
    boolean addUserResolve(long uid,long qid);

    /**
     * 查询用户是否解决过这个问题
     * @param uid
     * @param qid
     * @return
     */
    int queryUserResolveQuestion(long uid,long qid);

    /**
     * 得到各个解决难度的数量
     * @param uid
     * @return
     */
    UserResolve getPerDifficultySolve(long uid);

    /**
     * 拿到某个难度的题目数量
     * @param difficulty
     * @return
     */
    int countPerDifficultyCount(String difficulty);
}
