package com.chen.leo.netty.handler;

import com.chen.leo.proto.TransportRequest;
import com.chen.leo.proxy.TransportEventProxy;
import com.chen.leo.session.Session;
import com.chen.leo.session.SessionManager;
import io.netty.channel.ChannelHandlerContext;

public class MessageReceivedHandler extends TransportEventHandler {

    public MessageReceivedHandler() {
    }

    public MessageReceivedHandler(TransportEventProxy transportEventProxy) {
        super(transportEventProxy);
    }

    public MessageReceivedHandler(TransportEventProxy transportEventProxy, SessionManager sessionManager) {
        super(transportEventProxy, sessionManager);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        TransportRequest transportRequest = (TransportRequest) msg;
        if (transportEventProxy != null) {
            String sessionId = sessionManager.getSessionId(ctx);
            Session session = sessionManager.getSession(sessionId);
            transportEventProxy.transportRequest(session, transportRequest);
        }
    }
}
