package com.ckw.common.pojo;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelMatcher;

public class MyMacher implements ChannelMatcher {

    private String id;

    public MyMacher(String id){
        this.id = id;
    }

    @Override
    public boolean matches(Channel channel) {
        return channel.id().asLongText().equals(this.id);
    }
}
