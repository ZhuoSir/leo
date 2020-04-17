package com.chen.leo.netty.handler;

import com.chen.leo.proxy.TransportEventProxy;
import io.netty.channel.ChannelInboundHandlerAdapter;

public abstract class TransportEventHandler extends ChannelInboundHandlerAdapter {

    protected TransportEventProxy transportEventProxy;

    public TransportEventHandler(TransportEventProxy transportEventProxy) {
        this.transportEventProxy = transportEventProxy;
    }
}
