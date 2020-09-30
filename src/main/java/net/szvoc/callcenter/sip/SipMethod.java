package net.szvoc.callcenter.sip;

public interface SipMethod {
    String PRACK = "PRACK";
    String INVITE = "INVITE";
    String ACK = "ACK";
    String BYE = "BYE";
    String CANCEL = "CANCEL";
    String OPTIONS = "OPTIONS";
    String MESSAGE = "MESSAGE";
    String INFO = "INFO";
    String UPDATE = "UPDATE";
    String REGISTER = "REGISTER";
    String REFER = "REFER";
    String NOTIFY = "NOTIFY";
    String PUBLISH = "PUBLISH";
    String SUBSCRIBE = "SUBSCRIBE";

    static String[] all() {
        return new String[]{PRACK, INVITE, ACK, BYE, CANCEL, OPTIONS, MESSAGE, INFO, UPDATE, REGISTER, REFER, NOTIFY, PUBLISH, SUBSCRIBE};
    }
}
