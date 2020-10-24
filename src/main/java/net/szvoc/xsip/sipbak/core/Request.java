package net.szvoc.xsip.sipbak.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;

import java.net.InetSocketAddress;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Request implements Message {
    private String rawString;
    private HeaderSpec headers;
    private InetSocketAddress sender;
    private InetSocketAddress recipient;

    @Getter
    private String method;

    @Getter
    private String uri;

    @Getter
    private String version;

    @Getter
    private String body;

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
        return this.method;
    }

    @Override
    public InetSocketAddress sender() {
        return this.sender;
    }

    @Override
    public InetSocketAddress recipient() {
        return this.recipient;
    }

    public Request(InetSocketAddress sender, InetSocketAddress recipient, String method, String uri, String version, HeaderSpec headers, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.headers = headers;
        this.body = body;
        this.rawString = method + " " + uri + " " + version + "\r\n" + headers.toString() + "\r\n\r\n" + body;
    }

    private Request(InetSocketAddress sender, InetSocketAddress recipient, String rawString, String method, String uri, String version, HeaderSpec headers, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.headers = headers;
        this.body = body;
        this.rawString = rawString;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Header> T header(String name) {
        return (T) this.headers.header(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Header> T header(String name, Class<T> type) {
        return (T) this.headers.header(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Header> List<T> headers(String name) {
        return (List<T>) this.headers.headers(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Header> List<T> headers(String name, Class<T> type) {
        return (List<T>) this.headers.headers(name);
    }

    @Override
    public List<Header> headers() {
        return this.headers.headers();
    }

    public static Request parse(InetSocketAddress sender, InetSocketAddress recipient, String rawString) {
        // 解析URI
        var to = rawString.indexOf("\r\n");
        var temp = rawString.substring(0, to).split("\\s+");
        var method = temp[0];
        var uri = temp[1];
        var version = temp[2];

        // 解析header
        var from = to + 2;
        to = rawString.indexOf("\r\n\r\n", from);
        var headerStr = to > 0 ? rawString.substring(from, to) : rawString.substring(from);
        var headers = HeaderSpec.parse(headerStr);
        // 解析body
        var bodyStr = to > 0 ? rawString.substring(to + 4) : "";

        return new Request(sender, recipient, rawString, method, uri, version, headers, bodyStr);
    }
}
