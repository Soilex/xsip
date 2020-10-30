package net.szvoc.xsip.sip.header;

import lombok.Getter;

import java.util.Arrays;

public enum HeaderName {
    UNKNOWN(null), //未知类型
    EMPTY_LINE(""), // 空行
    // RFC 3261
    ACCEPT("Accept"),
    ACCEPT_ENCODING("Accept-Encoding"),
    ACCEPT_LANGUAGE("Accept-Language"),
    ALERT_INFO("Alert-Info"),
    ALLOW("Allow"),
    AUTHENTICATION_INFO("Authentication-Info"),
    AUTHORIZATION("Authorization"),
    CALL_ID("Call-ID"),
    CALL_INFO("Call-Info"),
    CONTACT("Contact"),
    CONTENT_DISPOSITION("Content-Disposition"),
    CONTENT_ENCODING("Content-Encoding"),
    CONTENT_LANGUAGE("Content-Language"),
    CONTENT_LENGTH("Content-Length"),
    CONTENT_TYPE("Content-Type"),
    CSEQ("CSeq"),
    DATE("DATE"),
    ERROR_INFO("Error-Info"),
    EXPIRES("Expires"),
    FROM("From"),
    IN_REPLY_TO("In-Reply-To"),
    MAX_FORWARDS("Max-Forwards"),
    MIN_EXPIRES("Min-Expires"),
    MIME_VERSION("MIME-Version"),
    ORGANIZATION("Organization"),
    PRIORITY("Priority"),
    PROXY_AUTHENTICATE("Proxy-Authenticate"),
    PROXY_AUTHORIZATION("Proxy-Authorization"),
    PROXY_REQUIRE("Proxy-Require"),
    RECORD_ROUTE("Record-Route"),
    REPLY_TO("Reply-To"),
    REQUIRE("Require"),
    RETRY_AFTER("Retry-After"),
    ROUTE("Route"),
    SERVER("Server"),
    SUBJECT("Subject"),
    SUPPORTED("Supported"),
    TIMESTAMP("Timestamp"),
    TO("To"),
    UNSUPPORTED("Unsupported"),
    USER_AGENT("User-Agent"),
    VIA("Via"),
    WARNING("Warning"),
    WWW_AUTHENTICATE("WWW-Authenticate"),
    // RFC 3262
    RACK("RAck"),
    RSEQ("RSeq"),
    // RFC 3265
    EVENT("EVENT"),
    ALLOW_EVENTS("Allow-Events"),
    SUBSCRIPTION_STATE("Subscription-State"),
    // RFC 3326
    REASON("Reason"),
    // RFC 3891
    Replaces("Replaces"),
    // RFC 3892
    ReferredBy("ReferredBy"),
    // RFC 3903
    SIP_ETAG("SIP-ETag"),
    SIP_IF_MATCH("SIP-If-Match"),
    // RFC 3911
    JOIN("Join"),
    // RFC 4028
    MIN_SE("Min-SE"),
    SESSION_EXPIRES("Session-Expires");

    private String text;

    HeaderName(String text) {
        this.text = text;
    }

    public String text() {
        return this.text;
    }

    public static HeaderName textOf(String text) {
        return Arrays.stream(values())
                .filter(c -> c.text != null && c.text.equalsIgnoreCase(text))
                .findAny()
                .orElse(null);
    }
}
