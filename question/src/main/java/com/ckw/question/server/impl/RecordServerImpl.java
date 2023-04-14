package com.ckw.question.server.impl;

import com.ckw.question.mapper.RecordMapper;
import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.question.server.RecordServer;
import com.ckw.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecordServerImpl implements RecordServer {
    @Autowired
    private RecordMapper recordMapper;



    @Override
    public List<SubmitRecord> getSubmitRecordList(int uid, int qid, int page) {
        page--;
        return recordMapper.getSubmitRecordList(uid,qid,page);
    }

    @Override
    public List<SubmitRecord> getSubmitRecordList(int qid, int page) {
        page--;
        return recordMapper.getSubmitRecordListWithQid(qid,page);
    }

    @Override
    public List<SubmitRecord> getSubmitRecordListWithUid(int uid, int page) {
        page--;
        return recordMapper.getSubmitRecordListWithUid(uid,page);
    }


}
