package com.chen.leo.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class LeoProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    /**
     * leo协议封装
     *
     * 协议名长度 | 协议名 | 版本号 |
     *
     * */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        int protocollength = msg.readInt();

    }
}
