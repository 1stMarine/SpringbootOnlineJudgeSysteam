package com.ckw.judger.server;

import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;

public interface JudgeServer {

    public TestResult doJudge(TestPack testPack);

}
