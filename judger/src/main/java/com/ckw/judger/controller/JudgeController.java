package com.ckw.judger.controller;

import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;
import com.ckw.judger.server.Impl.JudgeServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 凯威
 */
@RestController
public class JudgeController {

    @Autowired
    public JudgeServerImpl judgeServer;

    @PostMapping("/judge")
    public TestResult judgeAll(@RequestBody TestPack testPack){
        System.out.println(testPack);
        /**
         * 判题
         */
        TestResult testResult = judgeServer.doJudge(testPack);

        return testResult;
    }
}
