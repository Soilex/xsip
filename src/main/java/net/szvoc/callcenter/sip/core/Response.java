package net.szvoc.callcenter.sip.core;

import lombok.Getter;
import lombok.var;

import java.net.InetSocketAddress;
import java.util.List;

public class Response implements Message {
    private String rawString;
    private HeaderSpec headers;
    private InetSocketAddress sender;
    private InetSocketAddress recipient;

    @Getter
    private String version;

    @Getter
    private int status;

    @Getter
    private String statusText;

    @Getter
    private String body;

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Header> T header(String name) {
        return (T) this.headers.header(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Header> List<T> headers(String name) {
        return (List<T>) this.headers.headers(name);
    }

    @Override
    public List<Header> headers() {
        return this.headers.headers();
    }

    @Override
    public byte[] buffer() {
        return this.rawString.getBytes(SipOptions.CHARSET);
    }

    @Override
    public String string() {
        return rawString;
    }

    @Override
    public String method() {
        return this.header(HeaderName.CSEQ).value();
    }

    @Override
    public InetSocketAddress sender() {
        return this.sender;
    }

    @Override
    public InetSocketAddress recipient() {
        return this.recipient;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Header> T header(String name, Class<T> type) {
        return (T) this.headers.header(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Header> List<T> headers(String name, Class<T> type) {
        return (List<T>) this.headers.headers(name);
    }

    public Response(InetSocketAddress sender, InetSocketAddress recipient, String version, SipStatus status, HeaderSpec headers, String body) {
        this(sender, recipient, version + " " + status.status() + " " + status.statusText() + "\r\n" + headers + "\r\n" + body, version, status.status(), status.statusText(), headers, body);
    }

    private Response(InetSocketAddress sender, InetSocketAddress recipient, String rawString, String version, int status, String statusText, HeaderSpec headers, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.version = version;
        this.status = status;
        this.statusText = statusText;
        this.headers = headers;
        this.body = body;
        this.rawString = rawString;
    }

    public static Response parse(InetSocketAddress sender, InetSocketAddress recipient, String rawString) {
        // 解析URI
        var to = rawString.indexOf("\r\n");
        var temp = rawString.substring(0, to).split("\\s+");
        var version = temp[0];
        var status = Integer.parseInt(temp[1]);
        var statusText = temp[2];

        // 解析header
        var from = to + 2;
        to = rawString.indexOf("\r\n\r\n", from);
        var headerStr = to > 0 ? rawString.substring(from, to) : rawString.substring(from);
        var headers = HeaderSpec.parse(headerStr);
        // 解析body
        var bodyStr = to > 0 ? rawString.substring(to + 4) : "";

        return new Response(sender, recipient, rawString, version, status, statusText, headers, bodyStr);
    }
}
