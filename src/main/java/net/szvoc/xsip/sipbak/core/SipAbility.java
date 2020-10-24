package net.szvoc.xsip.sipbak.core;

public interface SipAbility {
    String TIMER = "timer";
    String PRECONDITION = "precondition";
    String PATH = "path";
    String REPLACES = "replaces";
    String SIP_100REL = "100rel";
    String NO_REFER_SUB = "norefersub";

    static String[] all() {
        return new String[]{SIP_100REL/*, TIMER, PRECONDITION, PATH, REPLACES, NO_REFER_SUB*/};
    }
}
