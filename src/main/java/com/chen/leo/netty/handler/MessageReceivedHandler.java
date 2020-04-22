package com.chen.leo.netty.handler;

import com.chen.leo.common.Constants;
import com.chen.leo.proto.TransportRequest;
import com.chen.leo.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class MessageReceivedHandler extends TransportEventHandler {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        TransportRequest transportRequest = (TransportRequest) msg;
        if (transportEventProxy != null) {
            String sessionId = ctx.channel().attr(AttributeKey.valueOf(Constants.SessionConfig.SESSION_KEY)).get().toString();
            Session session = sessionManager.getSession(sessionId);
            transportEventProxy.transportRequest(session, transportRequest);
        }
    }
}
