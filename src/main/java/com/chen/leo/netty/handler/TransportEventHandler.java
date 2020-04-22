package com.chen.leo.netty.handler;

import com.chen.leo.proxy.TransportEventProxy;
import com.chen.leo.session.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public abstract class TransportEventHandler extends ChannelInboundHandlerAdapter {

    protected TransportEventProxy transportEventProxy;

    protected SessionManager sessionManager;

    public TransportEventHandler() {
    }

    public TransportEventHandler(TransportEventProxy transportEventProxy) {
        this.transportEventProxy = transportEventProxy;
    }

    public TransportEventHandler(TransportEventProxy transportEventProxy, SessionManager sessionManager) {
        this.transportEventProxy = transportEventProxy;
        this.sessionManager = sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setTransportEventProxy(TransportEventProxy transportEventProxy) {
        this.transportEventProxy = transportEventProxy;
    }
}
