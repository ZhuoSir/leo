package com.chen.leo.netty.handler;

import com.chen.leo.proxy.TransportEventProxy;

public class MessageReceivedHandler extends TransportEventHandler {

    public MessageReceivedHandler(TransportEventProxy transportEventProxy) {
        super(transportEventProxy);
    }
}
