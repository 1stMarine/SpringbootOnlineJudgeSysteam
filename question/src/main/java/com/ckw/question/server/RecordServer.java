package com.ckw.question.server;

import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.user.pojo.User;

import java.util.List;
import java.util.Map;

public interface RecordServer {

    List<SubmitRecord> getSubmitRecordList(long uid,long qid,int page);

    List<SubmitRecord> getSubmitRecordList(long qid,int page);

    List<SubmitRecord> getSubmitRecordListWithUid(long uid,int page);


}
