package com.chen.leo.proto;

public abstract class LeoResponse implements Response {

    private Header header;

    public void setHeader(Header header) {
        this.header = header;
    }

    public Header getHeader() {
        return header;
    }
}
