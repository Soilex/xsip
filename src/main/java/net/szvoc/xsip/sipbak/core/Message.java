package net.szvoc.xsip.sipbak.core;

import java.net.InetSocketAddress;
import java.util.List;

public interface Message {
    byte[] buffer();

    String string();

    String method();

    <T extends Header> T header(String name);

    <T extends Header> T header(String name, Class<T> type);

    <T extends Header> List<T> headers(String name);

    <T extends Header> List<T> headers(String name, Class<T> type);

    List<Header> headers();

    InetSocketAddress sender();

    InetSocketAddress recipient();
}
