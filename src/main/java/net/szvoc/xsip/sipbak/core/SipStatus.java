package net.szvoc.xsip.sipbak.core;

public enum SipStatus {
    OK(200, "OK"),
    UNAUTHORIZED(401, "Unauthorized"),
    TRYING(100, "Trying"),
    RINGING(180, "Ringing"),
    Forwarded(181, "Call is Being Forwarded"),
    Queued(182, "Queued"),
    SessionProgress(183, "Session Progress"),

    ;

    private int status;
    private String statusText;

    public int status() {
        return this.status;
    }

    public String statusText() {
        return this.statusText;
    }

    public boolean isOk() {
        return this.status == OK.status;
    }

    SipStatus(int status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }
}
