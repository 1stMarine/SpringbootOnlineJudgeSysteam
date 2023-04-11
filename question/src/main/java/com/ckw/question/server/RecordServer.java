package com.ckw.question.server;

import com.ckw.judger.pojo.SubmitRecord;

import java.util.List;

public interface RecordServer {

    List<SubmitRecord> getSubmitRecordList(int uid,int qid,int page);

    List<SubmitRecord> getSubmitRecordList(int qid,int page);

    List<SubmitRecord> getSubmitRecordListWithUid(int uid,int page);
}
