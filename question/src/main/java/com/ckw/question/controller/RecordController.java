package com.ckw.question.controller;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.question.server.impl.RecordServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 凯威
 */
@RestController
public class RecordController {

    @Autowired
    private RecordServerImpl recordServer;

    @GetMapping("/getSubmitRecords/{uid}/{qid}/{page}")
    public Object getSubmitRecordsWithUidAndQid(@PathVariable int uid,@PathVariable int qid,@PathVariable int page){
        return new Message(State.SUCCESS,recordServer.getSubmitRecordList(uid,qid,page),"获取成功!");
    }
    @GetMapping("/getSubmitRecordsWithQid/{qid}/{page}")
    public Object getSubmitRecordsWithQid(@PathVariable int qid,@PathVariable int page){
        return new Message(State.SUCCESS,recordServer.getSubmitRecordList(qid,page),"获取成功!");
    }

    @GetMapping("/getSubmitRecordsWithUid/{uid}/{page}")
    public Object getSubmitRecordsWithUid(@PathVariable int uid,@PathVariable int page){
        return new Message(State.SUCCESS,recordServer.getSubmitRecordListWithUid(uid,page),"获取成功!");
    }

}
