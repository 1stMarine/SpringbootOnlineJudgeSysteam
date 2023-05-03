package com.ckw.question.mapper;

import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.question.pojo.MatchResult;
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
}
