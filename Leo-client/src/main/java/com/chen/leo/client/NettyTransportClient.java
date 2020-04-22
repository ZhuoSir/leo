package com.chen.leo.client;

import com.chen.leo.codec.MessageDecoder;
import com.chen.leo.codec.MessageEncoder;
import com.chen.leo.exeception.ServerConnectionException;
import com.chen.leo.handler.HeartBeatHandler;
import com.chen.leo.proto.TransportResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

public class NettyTransportClient implements TransportClient {

    private Logger logger = Logger.getLogger(NettyTransportClient.class);

    Bootstrap bootstrap;

    EventLoopGroup worker;

    Channel channel;

    private Integer workThreadNumber;


    @Override
    public void init(Integer port) {
        if (null == workThreadNumber) workThreadNumber = 5;
        worker = new NioEventLoopGroup(workThreadNumber);

        bootstrap = new Bootstrap();
        bootstrap.group(worker);
        bootstrap.channel(NioSocketChannel.class);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("messageDecoder", new MessageDecoder(TransportResponse.class));
                pipeline.addLast("messageEncoder", new MessageEncoder());
                pipeline.addLast("heatbeatHandler", new HeartBeatHandler(1));
            }
        }).option(ChannelOption.SO_BACKLOG, 128);
    }

    @Override
    public Session connect(String ip, int port, boolean sync) throws ServerConnectionException {

        Session session = null;

        try {
            bootstrap.remoteAddress(new InetSocketAddress(ip, port));
            ChannelFuture future = bootstrap.connect();
            if (sync) {
                future.sync();
            }

            channel = future.channel();
            session = new Session(channel);

            logger.info("Client has connected to server successfully...");
        } catch (InterruptedException e) {
            logger.info("Client has connected to server failed...");
            logger.error(e);
        }

        return session;
    }


    public void setWorkThreadNumber(Integer workThreadNumber) {
        this.workThreadNumber = workThreadNumber;
    }
}
