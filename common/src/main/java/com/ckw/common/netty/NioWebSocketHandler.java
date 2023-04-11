package com.ckw.common.netty;


import com.ckw.common.utils.RequestUriUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket连接数据接受处理类
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class NioWebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Autowired
    private NioWebSocketChannelPool webSocketChannelPool;
    @Autowired
    private WebSocketProperties webSocketProperties;

    public static Map<String,ChannelHandlerContext> userWebSocketId = new HashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端连接：{}", ctx.channel().id());
        webSocketChannelPool.addChannel(ctx.channel());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端断开连接：{}", ctx.channel().id());
        webSocketChannelPool.removeChannel(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.channel().flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 根据请求数据类型进行分发处理
        if (frame instanceof PingWebSocketFrame) {
            pingWebSocketFrameHandler(ctx, (PingWebSocketFrame) frame);
        } else if (frame instanceof TextWebSocketFrame) {
            textWebSocketFrameHandler(ctx, "","","");
        } else if (frame instanceof CloseWebSocketFrame) {
            closeWebSocketFrameHandler(ctx, (CloseWebSocketFrame) frame);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端请求数据类型：{}", msg.getClass());
        if (msg instanceof FullHttpRequest) {
            fullHttpRequestHandler(ctx, (FullHttpRequest) msg);
        }
        super.channelRead(ctx, msg);
    }

    /**
     * 处理连接请求，客户端WebSocket发送握手包时会执行这一次请求
     * @param ctx
     * @param request
     */
    private void fullHttpRequestHandler(ChannelHandlerContext ctx, FullHttpRequest request) {
        String uri = request.uri();
        Map<String, String> params = RequestUriUtils.getParams(uri);
//        存下用户的socket
        userWebSocketId.put(params.get("id"),ctx);
        log.info("客户端请求参数：{}", params);
        // 判断请求路径是否跟配置中的一致
        if (webSocketProperties.getPath().equals(RequestUriUtils.getBasePath(uri))){
            // 因为有可能携带了参数，导致客户端一直无法返回握手包，因此在校验通过后，重置请求路径
            request.setUri(webSocketProperties.getPath());
        }else{
            ctx.close();
        }
    }

    /**
     * 客户端发送断开请求处理
     * @param ctx
     * @param frame
     */
    private void closeWebSocketFrameHandler(ChannelHandlerContext ctx, CloseWebSocketFrame frame) {
        ctx.close();
    }

    /**
     * 创建连接之后，客户端发送的消息都会在这里处理
     * @param ctx

     */
    public static void textWebSocketFrameHandler(ChannelHandlerContext ctx, String code,String title,String message) {
        // 客户端发送过来的内容不进行业务处理，原样返回
        ctx.channel().writeAndFlush(new TextWebSocketFrame("" +
                "{\"code\":" + code + "," +
                "\"title\":\"" + title + "\"," +
                "\"message\":\"" + message + "\"}"
        ));
    }



    /**
     * 处理客户端心跳包
     * @param ctx
     * @param frame
     */
    private void pingWebSocketFrameHandler(ChannelHandlerContext ctx, PingWebSocketFrame frame) {
        ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
    }
}
