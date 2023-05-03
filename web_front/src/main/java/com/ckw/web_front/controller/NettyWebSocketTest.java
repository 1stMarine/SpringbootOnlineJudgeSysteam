package com.ckw.web_front.controller;

import com.ckw.common.netty.NioWebSocketHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NettyWebSocketTest {

    @GetMapping("/testSocket/{id}")
    public String test(@PathVariable String id){
        NioWebSocketHandler.textWebSocketFrameHandler(
                NioWebSocketHandler.userWebSocketId.get(id),
                "40001","成功","成功接收"
        );
        return "OK";
    }
}
