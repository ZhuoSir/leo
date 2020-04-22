package com.chen.leo.netty.handler;

import com.chen.leo.proto.*;
import com.chen.leo.proxy.TransportEventProxy;
import com.chen.leo.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatReqHandler extends TransportEventHandler {

    public HeartBeatReqHandler(TransportEventProxy transportEventProxy) {
        super(transportEventProxy);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        if (sessionManager != null) {
            Session session = sessionManager.createSession(ctx);
            if (transportEventProxy != null) {
                transportEventProxy.connectEvent(session);
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        TransportRequest request = (TransportRequest) msg;

        // 心跳类型
        if (request.getHeader() != null
                && request.getLine().getCmd().equals(Request.REQUESTTYPE_HEARTBEAT)) {
            // 心跳响应
            Response response = buildHeartBeatResponse();
            ctx.writeAndFlush(response);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public Response buildHeartBeatResponse() {
        TransportResponse.Builder builder = new TransportResponse.Builder();
        return builder.heartBeatResp().build();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state()== IdleState.WRITER_IDLE){
                // 30s 没有写事件了，连接关闭
                ctx.channel().close();
            }
        }
    }
}
