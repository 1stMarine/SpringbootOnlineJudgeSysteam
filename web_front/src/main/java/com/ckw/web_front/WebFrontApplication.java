package com.ckw.web_front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 凯威
 */


@EnableScheduling // 开启定时任务
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@ComponentScan({"com.ckw.judger","com.ckw.common","com.ckw.question","com.ckw.user","com.ckw.web_front","com.ckw.match"})
@MapperScan("com.ckw")
public class WebFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFrontApplication.class, args);
    }

}
