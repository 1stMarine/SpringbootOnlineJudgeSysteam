package com.ckw.question.controller;

import com.ckw.common.netty.NioWebSocketChannelPool;
import com.ckw.common.netty.NioWebSocketHandler;
import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.question.pojo.Question;
import com.ckw.question.server.impl.QuestionServerImpl;
import com.ckw.question.server.impl.UserQuestionServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Object getQuestionList(@PathVariable int page,@PathVariable long uid){
        page--;
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
    public Object getQuestion(@PathVariable long id){
        return new Message(State.SUCCESS,questionServer.queryQuestion(id),"获取成功!");
    }

    @GetMapping(value = "/getUserResolve/{id}")
    public Object getUserResolveQuestions(@PathVariable long id){
        return new Message(State.SUCCESS,questionServer.getUserResolveQuestionId(id),"操作成功!");
    }

    @GetMapping(value = "/countQuestion")
    public Object countQuestion(){
        return new Message(State.SUCCESS,questionServer.countQuestion(),"获取成功!");
    }

    @GetMapping(value = "/getPerDifficultySolve/{uid}")
    public Object getPerDifficultySolve(@PathVariable long uid){
        return new Message(State.SUCCESS,userQuestionService.getUserResolve(uid),"获取成功！");
    }

    /**
     * 百分比的方式
     * @param uid
     * @return
     */
    @GetMapping(value = "/getPerDifficultySolveWithPercent/{uid}")
    public Object getPerDifficultySolveWithPercent(@PathVariable long uid){
        return new Message(State.SUCCESS,userQuestionService.getUserResolveWithPercent(uid),"获取成功！");
    }

    @GetMapping("/getQuestionTestSample/{qid}")
    public Object getQuestionTestSample(@PathVariable String qid){
        return new Message(State.SUCCESS,questionServer.getQuestionTestSample(qid),"获取成功!");
    }

    @PostMapping(value = "/changeQuestionInfo")
    public Object changeQuestionInfo(@RequestBody Question question){
        boolean change = questionServer.changeQuestionInfo(question);
        return new Message(change ? State.SUCCESS : State.FAILURE,change,change ? "更改成功":"更改失败");
    }

    @DeleteMapping("/deleteQuestion")
    public Object deleteQuestion(@Param("qid") String qid){
        boolean delete = questionServer.deleteQuestion(qid);
        return new Message(delete ? State.SUCCESS : State.FAILURE,delete,delete?"删除成功":"删除失败");
    }

    @PostMapping("/addQuestion")
    public Object addQuestion(@RequestBody Question question){
        boolean add = questionServer.addQuestion(question);
        return new Message(add ? State.SUCCESS : State.FAILURE,add,add?"添加成功!":"添加失败!");
    }
}
