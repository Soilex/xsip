package net.szvoc.callcenter.sip;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SipOptions {
    public final static String VERSION = "SIP/2.0";
    public final static String USER_AGENT = "call-center/1.0.0";
    public final static String REALM = "szvoc.net";
    public final static String[] ALLOWS = SipMethod.all();
    public final static String[] ABILITES = Ability.all();
    public final static Charset CHARSET = StandardCharsets.UTF_8;
}
