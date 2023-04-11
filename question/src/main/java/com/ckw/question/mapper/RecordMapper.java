package com.ckw.question.mapper;

import com.ckw.judger.pojo.SubmitRecord;
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
//
//    List<SubmitRecord> getSubmitRecordListWithUid(int uid,int page);
//
    List<SubmitRecord> getSubmitRecordListWithQid(int qid,int page);

    List<SubmitRecord> getSubmitRecordListWithUid(int uid,int page);
}
