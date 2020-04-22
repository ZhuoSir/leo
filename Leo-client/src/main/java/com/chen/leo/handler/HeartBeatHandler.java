package com.chen.leo.handler;

import com.chen.leo.client.NettyTransportClient;
import com.chen.leo.proto.Response;
import com.chen.leo.proto.TransportRequest;
import com.chen.leo.proto.TransportResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class HeartBeatHandler extends TransportHandler {

    private Logger logger = Logger.getLogger(HeartBeatHandler.class);

    private int second = 5;

    public HeartBeatHandler(int second) {
        this.second = second;
    }

    private static TransportRequest heatBeatReq = new TransportRequest.Builder().heatBeat().build();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        heatbeat(ctx.channel());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        TransportResponse transportResponse = (TransportResponse) msg;
        if (transportResponse.getLine().getCmd().equals(Response.REPONSETYPE_HEARTBEAT)) {
            logger.info("pong...");
        }

        ctx.fireChannelRead(msg);
    }

    private void heatbeat(Channel channel) {

        ScheduledFuture<?> future = channel.eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                if (channel.isActive()) {
                    logger.info("ping...");
                    channel.writeAndFlush(heatBeatReq);
                } else {
                    logger.info("The connection had broken, cancel the task that will send a heart beat.");
                    channel.closeFuture();
                    throw new RuntimeException();
                }
            }
        }, second, TimeUnit.SECONDS);

        future.addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if (future.isSuccess()) {
                    heatbeat(channel);
                }
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.channel().close();
    }
}
