package com.ckw.question.controller;

import com.ckw.common.netty.NioWebSocketChannelPool;
import com.ckw.common.netty.NioWebSocketHandler;
import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.question.server.impl.QuestionServerImpl;
import com.ckw.question.server.impl.UserQuestionServiceImpl;
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

    @Autowired
    private UserQuestionServiceImpl userQuestionService;

    @PostMapping(value = "/uploadQuestion")
    public Object upload(MultipartFile file){
        questionServer.uploadQuestionXml(file);
        return new Message(State.SUCCESS,null,"提交成功!");
    }

    @GetMapping(value = "/getQuestionList/{page}/{uid}")
    public Object getQuestionList(@PathVariable int page,@PathVariable int uid){
        page--;
        System.out.println(page+"===="+uid);
        return new Message(State.SUCCESS,questionServer.queryQuestionList(page,uid),"获取成功!");
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

    @GetMapping(value = "/getUserResolve/{id}")
    public Object getUserResolveQuestions(@PathVariable int id){
        return new Message(State.SUCCESS,questionServer.getUserResolveQuestionId(id),"操作成功!");
    }

    @GetMapping(value = "/countQuestion")
    public Object countQuestion(){
        return new Message(State.SUCCESS,questionServer.countQuestion(),"获取成功!");
    }

    @GetMapping(value = "/getPerDifficultySolve/{uid}")
    public Object getPerDifficultySolve(@PathVariable int uid){
        return new Message(State.SUCCESS,userQuestionService.getUserResolve(uid),"获取成功！");
    }

    /**
     * 百分比的方式
     * @param uid
     * @return
     */
    @GetMapping(value = "/getPerDifficultySolveWithPercent/{uid}")
    public Object getPerDifficultySolveWithPercent(@PathVariable int uid){
        return new Message(State.SUCCESS,userQuestionService.getUserResolveWithPercent(uid),"获取成功！");
    }
}
