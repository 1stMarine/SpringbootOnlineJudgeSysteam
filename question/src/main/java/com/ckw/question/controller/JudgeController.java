package com.ckw.question.controller;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;
import com.ckw.question.pojo.Type;
import com.ckw.question.server.impl.JudgeServerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 凯威
 */
@RestController
@Slf4j
public class JudgeController {

    @Autowired
    public JudgeServerImpl judgeServer;



    @PostMapping("/judge")
    public Object judgeAll(@RequestBody TestPack testPack){

        /**
         * 判题
         */
        TestResult testResult = null;
        boolean setting = false;
        if(testPack.getType().equals(Type.MATCH)){
            testResult = judgeServer.matchJudge(testPack);
            setting = judgeServer.settingForMatch(testResult,testPack);
            log.info("竞赛提交");
        }else if(testPack.getType().equals(Type.NORMAL)){
//            普通提交走这里
            log.info("普通提交");
            testResult = judgeServer.normalJudge(testPack);
            setting = judgeServer.setting(testResult, testPack);
        }else{
            return new Message(State.FAILURE,null,"提交失败,没有这个提交选项");
        }


        return new Message(State.SUCCESS,testResult, !setting ? "提交失败,请联系管理员!":"提交成功!");
    }

//    @PostMapping("/judgeTest")
//    public Object judgeTest(@RequestBody TestPack testPack){
//        System.out.println(testPack);
//        /**
//         * 判题
//         */
//        TestResult testResult = judgeServer.doJudge(testPack);
//        boolean setting = judgeServer.setting(testResult, testPack);
//
//        return new Message(State.SUCCESS,testResult, !setting ? "提交失败,请联系管理员!":"提交成功!");
//    }
}
