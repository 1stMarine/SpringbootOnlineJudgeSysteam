package com.ckw.question.controller;

import com.ckw.common.netty.NioWebSocketChannelPool;
import com.ckw.common.netty.NioWebSocketHandler;
import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.question.server.impl.QuestionServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 凯威
 */
@RestController
public class QuestionController {

    @Autowired
    private QuestionServerImpl questionServer;

    @PostMapping(value = "/uploadQuestion")
    public Object upload(MultipartFile file){
        questionServer.uploadQuestionXml(file);
        return new Message(State.SUCCESS,null,"提交成功!");
    }

    @GetMapping(value = "/getQuestionList/{page}")
    public Object getQuestionList(@PathVariable int page){

        page--;
        return new Message(State.SUCCESS,questionServer.queryQuestionList(page),"获取成功!");
    }

    @PostMapping(value = "/searchQuestion/{page}/{search}")
    public Object serachQuestion(@PathVariable int page,@PathVariable String search){
        page--;
        return new Message(State.SUCCESS,questionServer.querySearchQuestionList(page,search),"查询成功！");
    }

    @PostMapping(value = "/getQuestionSelect")
    public Object getQuestionSelect(){
        return new Message(State.SUCCESS,questionServer.getQuestionSelect(),"获取成功");
    }

    @GetMapping(value = "/getQuestion/{id}")
    public Object getQuestion(@PathVariable int id){
        return new Message(State.SUCCESS,questionServer.queryQuestion(id),"获取成功!");
    }
}
