package com.chen.leo.client;

import com.chen.leo.proto.TransportRequest;
import io.netty.channel.Channel;
import lombok.Data;


@Data
public class Session {

    private Channel channel;

    public Session(Channel channel) {
        this.channel = channel;
    }

    public void writeAndFlush(Object msg) {
        channel.writeAndFlush(msg);
    }
}
