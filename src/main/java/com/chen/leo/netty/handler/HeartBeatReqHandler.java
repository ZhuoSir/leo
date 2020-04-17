package com.chen.leo.netty.handler;

import com.chen.leo.proto.*;
import com.chen.leo.proxy.TransportEventProxy;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatReqHandler extends TransportEventHandler {

    public HeartBeatReqHandler(TransportEventProxy transportEventProxy) {
        super(transportEventProxy);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        TransportRequest request = (TransportRequest) msg;

        // 心跳类型
        if (request.getHeader() != null
                && request.getHeader().getType() == Request.REQUESTTYPE_HEARTBEAT) {
            // 心跳响应
            Response response = buildHeartBeatResponse();
            ctx.writeAndFlush(response);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public Response buildHeartBeatResponse() {
        TransportResponse response = new HeartBeatResponse();
        Header header = new Header();
        header.setType(Response.REPONSETYPE_HEARTBEAT);
        response.setHeader(header);
        return response;
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
