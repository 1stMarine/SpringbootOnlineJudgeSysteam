package com.ckw.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 凯威
 * Netty服务端
 */
@Slf4j
@Component
public class NioWebSocketServer implements InitializingBean, DisposableBean {

    @Autowired
    private WebSocketProperties webSocketProperties;
    @Autowired
    private NioWebSocketChannelInitializer webSocketChannelInitializer;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ChannelFuture channelFuture;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            bossGroup = new NioEventLoopGroup(webSocketProperties.getBoss());
            workGroup = new NioEventLoopGroup(webSocketProperties.getWork());

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024)
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(webSocketProperties.getPort())
                    .childHandler(webSocketChannelInitializer);

            channelFuture = serverBootstrap.bind().sync();
        } finally {
            if (channelFuture != null && channelFuture.isSuccess()) {
                log.info("Netty server startup on port: {} (websocket) with context path '{}'", webSocketProperties.getPort(), webSocketProperties.getPath());
            } else {
                log.error("Netty server startup failed.");
                if (bossGroup != null)
                    bossGroup.shutdownGracefully().sync();
                if (workGroup != null)
                    workGroup.shutdownGracefully().sync();
            }
        }
    }

    @Override
    public void destroy() throws Exception {
        log.info("Shutting down Netty server...");
        if (bossGroup != null)
            bossGroup.shutdownGracefully().sync();
        if (workGroup != null)
            workGroup.shutdownGracefully().sync();
        if (channelFuture != null)
            channelFuture.channel().closeFuture().syncUninterruptibly();
        log.info("Netty server shutdown.");
    }
}
