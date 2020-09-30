package net.szvoc.callcenter.sip;

public enum SipStatus {
    OK(200, "Ok"),
    UNAUTHORIZED(401, "Unauthorized"),
    TRYING(180, "Trying")
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
