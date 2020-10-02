package net.szvoc.callcenter.sip.core;

public interface SipMethod {
    String INVITE = "INVITE";
    String ACK = "ACK";
    String BYE = "BYE";
    String CANCEL = "CANCEL";
    String OPTIONS = "OPTIONS";
    String REGISTER = "REGISTER";
    String PRACK = "PRACK";
    String MESSAGE = "MESSAGE";
    String INFO = "INFO";
    String UPDATE = "UPDATE";
    String REFER = "REFER";
    String NOTIFY = "NOTIFY";
    String PUBLISH = "PUBLISH";
    String SUBSCRIBE = "SUBSCRIBE";

    static String[] all() {
        return new String[]{INVITE, ACK, BYE, CANCEL, OPTIONS, REGISTER, PRACK/*, MESSAGE, INFO, UPDATE, REFER, NOTIFY, PUBLISH, SUBSCRIBE*/};
    }
}
