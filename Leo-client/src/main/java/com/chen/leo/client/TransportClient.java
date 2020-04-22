package com.chen.leo.client;

import com.chen.leo.exeception.ServerConnectionException;

public interface TransportClient {

    void init(final Integer port);

    Session connect(String ip, int port, boolean sync) throws ServerConnectionException;
}
