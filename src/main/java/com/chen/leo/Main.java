package com.chen.leo;

import com.chen.leo.transport.NettyTransportServer;
import com.chen.leo.transport.TransportServer;

public class Main {

    public static void main(String[] args) {

        TransportServer transportServer = new NettyTransportServer();
        transportServer.init(7000);
        transportServer.start(true);
    }

}
