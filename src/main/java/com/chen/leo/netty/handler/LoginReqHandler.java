package com.chen.leo.netty.handler;

import com.chen.leo.proxy.TransportEventProxy;
import io.netty.channel.ChannelHandlerContext;

public class LoginReqHandler extends TransportEventHandler {

    public LoginReqHandler(TransportEventProxy transportEventProxy) {
        super(transportEventProxy);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

//        transportEventProxy.loginEvent();


    }
}
