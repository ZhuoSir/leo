package com.chen.leo.netty.handler;

import com.chen.leo.proxy.TransportEventProxy;
import io.netty.channel.ChannelHandlerContext;

public class LeoAnalysisHandler extends TransportEventHandler {

    public LeoAnalysisHandler(TransportEventProxy transportEventProxy) {
        super(transportEventProxy);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);



    }

}
