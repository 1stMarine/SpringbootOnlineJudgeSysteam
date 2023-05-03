package com.ckw.question.server;

import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;
import com.ckw.judger.pojo.TestSample;

import java.util.List;

public interface JudgeServer {

    TestResult matchJudge(TestPack testPack);

    TestResult normalJudge(TestPack testPack);

    public TestResult doJudge(TestPack testPack);

    public List<TestSample> getTestSamples(int id);

    boolean saveSubmitRecord(SubmitRecord submitRecord);

    void updateExperienceAndLevel(int qid,int id);


}
