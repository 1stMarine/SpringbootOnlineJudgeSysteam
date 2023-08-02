package com.ckw.judger.judger_v2;

import com.ckw.common.config.MinioConfig;
import com.ckw.common.config.DockerClientConfig;
import com.ckw.common.docker.DockerClientUtils;
import com.ckw.web_front.WebFrontApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WebFrontApplication.class)
public class JudgeV2Test {

    @Autowired
    private DockerClientUtils dockerClientUtils;

    @Autowired
    private DockerClientConfig  dockerClientConfig;

    @Autowired
    private MinioConfig minioConfig;

    @Test
    public void test(){
        System.out.println(minioConfig);
    }

}
