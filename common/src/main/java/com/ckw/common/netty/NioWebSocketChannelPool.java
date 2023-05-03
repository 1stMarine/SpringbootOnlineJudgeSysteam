package com.ckw.common.netty;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 凯威
 * WebSocket链接通道池
 */
@Slf4j
@Component
public class NioWebSocketChannelPool {

    private final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 新增一个客户端通道
     *
     * @param channel
     */
    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    /**
     * 移除一个客户端连接通道
     *
     * @param channel
     */
    public void removeChannel(Channel channel) {
        channels.remove(channel);
    }

}
