package com.ckw.common.netty;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 凯威
 * WebSocket 配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "chat.websocket")
public class WebSocketProperties {
    /**
     * 监听端口
     */
    private Integer port = 8081;
    /**
     * 请求路径
     */
    private String path = "/ws"; // 请求路径
    private Integer boss = 2; // bossGroup线程数
    private Integer work = 2; // workGroup线程数

}
