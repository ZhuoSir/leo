package com.chen.leo.netty.handler;

import com.chen.leo.common.Constants;
import com.chen.leo.proto.*;
import com.chen.leo.proxy.TransportEventProxy;
import com.chen.leo.session.Session;
import com.chen.leo.session.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.apache.log4j.Logger;

public class HeartBeatReqHandler extends TransportEventHandler {

    private Logger logger = Logger.getLogger(HeartBeatReqHandler.class);

    public HeartBeatReqHandler(TransportEventProxy transportEventProxy, SessionManager sessionManager) {
        super(transportEventProxy, sessionManager);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        if (sessionManager != null) {
            Session session = sessionManager.createSession(ctx);
            if (transportEventProxy != null) {
                transportEventProxy.connect(session);
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

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

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        String sessionId = sessionManager.getSessionId(ctx);
        logger.info(sessionId + " connection has disconnected..");
    }

    public Response buildHeartBeatResponse() {
        TransportResponse.Builder builder = new TransportResponse.Builder();
        return builder.heartBeatResp().build();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state()== IdleState.WRITER_IDLE){
                // 没有写事件了，连接关闭
                String sessionId = ctx.channel().attr(AttributeKey.valueOf(Constants.SessionConfig.SESSION_KEY)).get().toString();
                logger.info(sessionId + " connection has lost");
                ctx.channel().close();
            }
        }
    }
}
