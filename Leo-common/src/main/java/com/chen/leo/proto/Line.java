package com.chen.leo.proto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Line {

    private String cmd;

    private Map<String, String> params;

    private String uri;

    private int code;

    public Line() {
        params = new ConcurrentHashMap<>();
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Line{" +
                "cmd='" + cmd + '\'' +
                ", params=" + params +
                ", uri='" + uri + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
