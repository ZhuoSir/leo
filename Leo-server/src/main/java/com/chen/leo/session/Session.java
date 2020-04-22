package com.chen.leo.session;

import com.chen.leo.common.Constants;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class Session {

    private String id;

    private ChannelHandlerContext context;

    private Channel channel;

    public Session(String id, ChannelHandlerContext context) {
        this.id = id;
        this.context = context;
        this.channel = context.channel();
    }

    public void writeAndFlush(Object msg) {
        channel.writeAndFlush(msg);
    }

    public String getId() {
        return id;
    }

    public boolean isConnected() {
        return channel.isActive();
    }

    public void close() {
        channel.close();
    }

    public ChannelHandlerContext context() {
        return this.context;
    }

    public void attrs(String key, String value) {
        this.channel.attr(AttributeKey.valueOf(key)).set(value);
    }
}
