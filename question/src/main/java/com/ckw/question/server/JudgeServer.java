package com.ckw.question.server;

import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;
import com.ckw.judger.pojo.TestSample;

import java.util.List;

public interface JudgeServer {

    TestResult matchJudge(TestPack testPack);

    TestResult normalJudge(TestPack testPack);

    TestResult doJudge(TestPack testPack);

    List<TestSample> getTestSamples(long id);

    boolean saveSubmitRecord(SubmitRecord submitRecord);

    void updateExperienceAndLevel(long qid,long id);


}
