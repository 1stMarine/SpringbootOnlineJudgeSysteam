package com.ckw.judger.server.Impl;

import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;
import com.ckw.judger.pojo.TestSample;
import com.ckw.judger.server.JudgeServer;
import com.ckw.judger.judger.Judger;
import com.ckw.judger.utils.ContainerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class JudgeServerImpl implements JudgeServer {
    @Autowired
    public ContainerUtils containerUtils;
    @Override
    public TestResult doJudge(TestPack testPack) {
//        创建时间
        Long time = System.currentTimeMillis();
//        拉取测试样例
        List<TestSample> testSamples = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            testSamples.add(new TestSample("1",String.valueOf(i),String.valueOf(i),null,false));
        }
//        时间内空间
        testPack.setMemoryLimit(65536);
        testPack.setTimeLimit(1);
//          对测试对象进行设置
        testPack.setSubmitTime(time);
        testPack.setSubmitTimeFormat(
                new SimpleDateFormat("yyyy-MM-dd- HH:mm:ss").format(time)
        );

        testPack.setTestSampleList(testSamples);

        TestResult testResult = new TestResult();
        try {
            //          新建判题机运行
            Judger judger = new Judger(testPack);
            testResult = judger.run();
        }finally {
            //        删除判题机（异步）
            containerUtils.deleteContainer(testPack.getContainerId());
        }
        System.out.println(testResult);
        return testResult;
    }
}
