package com.chen.leo.proto;

public class TransportResponse implements Response {

    private Line line;

    private Header header;

    private Content content;

    public void setHeader(Header header) {
        this.header = header;
    }

    public Header getHeader() {
        return header;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Line getLine() {
        return line;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "TransportResponse{" +
                "line=" + line +
                ", header=" + header +
                ", content='" + content + '\'' +
                '}';
    }

    static public class Builder {

        private Line    line;
        private Header  header;
        private Content content;

        public Builder() {
            line    = new Line();
            header  = new Header();
            content = new Content();
        }

        public TransportResponse build() {
            TransportResponse transportResponse = new TransportResponse();
            transportResponse.setLine(line);
            transportResponse.setHeader(header);
            transportResponse.setContent(content);
            return transportResponse;
        }

        public Builder param(String key, String value) {
            line.getParams().put(key, value);
            return this;
        }

        public Builder uri(String uri) {
            line.setUri(uri);
            return this;
        }

        public Builder cmd(String cmd) {
            line.setCmd(cmd);
            return this;
        }

        public Builder code(int code) {
            line.setCode(code);
            return this;
        }

        public Builder header(String key, String content) {
            header.getHeaders().put(key, content);
            return this;
        }

        public Builder body(CharSequence body) {
            this.content.setBody(body);
            return this;
        }

        public Builder heartBeatResp() {
            line.setCmd(Response.REPONSETYPE_HEARTBEAT);
            line.setCode(Response.OK);
            return this;
        }
    }
}
