package com.ckw.web_front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAsync
@ComponentScan({"com.ckw.judger","com.ckw.common"})
public class WebFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFrontApplication.class, args);
    }

}
