package net.szvoc.callcenter.sip;

public interface Ability {
    String TIMER = "timer";
    String PRECONDITION = "precondition";
    String PATH = "path";
    String REPLACES = "replaces";
    String HUNDRED_REL = "100rel";
    String NO_REFER_SUB = "norefersub";

    static String[] all() {
        return new String[]{TIMER, PRECONDITION, PATH, REPLACES, HUNDRED_REL, NO_REFER_SUB};
    }
}
