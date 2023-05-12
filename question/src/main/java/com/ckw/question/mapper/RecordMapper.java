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

    boolean addRecord(SubmitRecord submitRecord);

//    List<SubmitRecord> getSubmitRecordList(int page);
//
    List<SubmitRecord> getSubmitRecordList(int uid,int qid,int page);
    List<SubmitRecord> getAllSubmitRecordList();
//
//    List<SubmitRecord> getSubmitRecordListWithUid(int uid,int page);
//
    List<SubmitRecord> getSubmitRecordListWithQid(int qid,int page);

    List<SubmitRecord> getSubmitRecordListWithUid(int uid,int page);

    List<String> getSubmitRecordCode();

    boolean addMatchRecord(SubmitRecord submitRecord,int mid);

    List<SubmitRecord> getAllMatchSubmitRecordOnlyAC(int mid);

    List<SubmitRecord> getAllMatchSubmitRecordNoLimit(int mid);

//    拿到比赛分数记录
    MatchResult getUserMatchResult(int mid,int uid);
//    更新比赛分数
    boolean updateUserMatchResult(MatchResult matchResult);
//    新增用户比赛分数
    boolean addUserMatchResult(MatchResult matchResult);
//    得到竞赛的所有结果
    List<MatchResult> getMatchResult(int mid);

//    得到用户解决过的问题
    List<Integer> getUserResolveRecord(int uid);

    /**
     * 添加用户解决的问题
     * @param uid
     * @param qid
     * @return
     */
    boolean addUserResolve(int uid,int qid);

    /**
     * 查询用户是否解决过这个问题
     * @param uid
     * @param qid
     * @return
     */
    int queryUserResolveQuestion(int uid,int qid);

    /**
     * 得到各个解决难度的数量
     * @param uid
     * @return
     */
    UserResolve getPerDifficultySolve(int uid);

    int countPerDifficultyCount(String difficulty);
}
