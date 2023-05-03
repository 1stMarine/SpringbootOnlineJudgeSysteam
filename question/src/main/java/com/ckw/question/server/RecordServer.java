package com.ckw.question.server;

import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.user.pojo.User;

import java.util.List;
import java.util.Map;

public interface RecordServer {

    List<SubmitRecord> getSubmitRecordList(int uid,int qid,int page);

    List<SubmitRecord> getSubmitRecordList(int qid,int page);

    List<SubmitRecord> getSubmitRecordListWithUid(int uid,int page);


}
