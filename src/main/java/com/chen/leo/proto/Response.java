package com.chen.leo.proto;

public interface Response {

    String REPONSETYPE_HEARTBEAT = "HB";

    String REPONSETYPE_COMMAND = "CMD";

    int OK = 200;

    int NOTFOUND = 404;

    int INTERNALERROR = 500;

}
