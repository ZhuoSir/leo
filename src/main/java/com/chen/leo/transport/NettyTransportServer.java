package com.chen.leo.transport;

import com.chen.leo.codec.MessageDecoder;
import com.chen.leo.codec.MessageEncoder;
import com.chen.leo.netty.handler.HeartBeatReqHandler;
import com.chen.leo.proto.Request;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.log4j.Logger;

public class NettyTransportServer implements  TransportServer {

    private Logger logger = Logger.getLogger(NettyTransportServer.class);

    private ServerBootstrap bootstrap;

    private EventLoopGroup parentGroup;

    private EventLoopGroup childGroup;

    private ChannelFuture future;

    private Channel sChannel;

    private final Integer THREADNUMBER = 10;

    private int threadNum = THREADNUMBER;

    private int port;

    private boolean sync;

    public NettyTransportServer() {
    }

    public NettyTransportServer(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void init(Integer port) {

        this.port = port;

        bootstrap   = new ServerBootstrap();
        parentGroup = new NioEventLoopGroup(threadNum);
        childGroup  = new NioEventLoopGroup(threadNum);

        bootstrap.group(parentGroup, childGroup);

        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast("messageDecoder", new MessageDecoder(Request.class));
                pipeline.addLast("messageEncoder", new MessageEncoder());
                pipeline.addLast(new IdleStateHandler(0, 15, 0));
                pipeline.addLast(new HeartBeatReqHandler());
            }
        }).option(ChannelOption.SO_BACKLOG, 128) //客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
                .childOption(ChannelOption.SO_KEEPALIVE, true); //保持连接检测对方主机是否崩溃，避免（服务器）永远阻塞于TCP连接的输入。

        future = bootstrap.bind(port);
    }

    @Override
    public void start(boolean sync) {

        try {
            this.sync = sync;
            if (sync) {
                future.sync();
            }
            logger.info(" TransportServer 启动中：线程数：" + threadNum + " 端口号：" + port + " 是否同步：" + this.sync);

            sChannel = future.channel();
            future.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        logger.info(" TransportServer 启动成功");
                    } else {
                        logger.info(" TransportServer 启动失败");
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e);
        }

    }
}
