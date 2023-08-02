package com.ckw.common.websocket;

import com.ckw.common.utils.SystemInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WebSocketUtil extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, String> params = handleUrlParam(session.getUri().toString());
        log.info("连接成功,参数 : " + params.toString());
        switch (Integer.valueOf(params.get("type"))){
            case 10000:
                log.info("连接判题机");
                break;
            case 10001:
                log.info("获取系统信息");
                SystemInfoUtils.callSysInfo(session);
                break;
        }
        // 连接建立后的处理逻辑
    }

    public Map<String, String> handleUrlParam(String requestUrl){
        // 解析URL中的参数
        String[] parts = requestUrl.split("\\?");
        String parameters = parts[1];
        String[] keyValuePairs = parameters.split("&");
        Map<String, String> params = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            String value = keyValue[1];
            params.put(key,value);
        }
        return params;
    }

    /**
     * 收到消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("收到消息");
        System.out.println(session);
        System.out.println(message);
        // 处理收到的消息逻辑
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        // 错误处理逻辑
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("关闭链接");
        System.out.println(session);
        System.out.println(status);
        // 连接关闭后的处理逻辑
    }

    public static void sendMessage(WebSocketSession session, String message) throws IOException {
        session.sendMessage(new TextMessage(message));
    }
}