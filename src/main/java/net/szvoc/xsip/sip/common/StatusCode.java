package net.szvoc.xsip.sip.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusCode {
    TRYING(100, "Trying"),
    RINGING(180, "Ringing"),
    FORWARDED(181, "Call Is Being Forwarded"),
    QUEUED(182, "Queued"),
    SESSION_PROGRESS(183, "Session Progress"),
    SUCCESS(200, "Success"),
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    MOVED_TEMPORARILY(302, "Moved Temporarily"),
    USE_PROXY(305, "Use Proxy"),
    ALTERNATIVE_SERVICE(380, "Alternative Service"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    GONE(410, "Gone"),
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
    REQUEST_URI_TOO_LARGE(414, "Request-URI Too Large"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    UNSUPPORTED_URI_SCHEME(416, "Unsupported URI Scheme"),
    BAD_EXTENSION(420, "Bad Extension"),
    EXTENSION_REQUIRED(421, "Extension Required"),
    INTERVAL_TOO_BRIEF(423, "Interval Too Brief"),
    TEMPORARILY_NOT_AVAILABLE(480, "Temporarily not available"),
    CALL_LEG_TRANSACTION_DOES_NOT_EXIST(481, "Call Leg/Transaction Does Not Exist"),
    LOOP_DETECTED(482, "Loop Detected"),
    TOO_MANY_HOPS(483, "Too Many Hops"),
    ADDRESS_INCOMPLETE(484, "Address Incomplete"),
    AMBIGUOUS(485, "Ambiguous"),
    BUSY_HERE(486, "Busy Here"),
    REQUEST_TERMINATED(487, "Request Terminated"),
    NOT_ACCEPTABLE_HERE(488, "Not Acceptable Here"),
    REQUEST_PENDING(491, "Request Pending"),
    UNDECIPHERABLE(493, "Undecipherable"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    SERVER_TIME_OUT(504, "Server Time-out"),
    SIP_VERSION_NOT_SUPPORTED(505, "SIP Version not supported"),
    MESSAGE_TOO_LARGE(513, "Message Too Large"),
    BUSY_EVERYWHERE(600, "Busy Everywhere"),
    DECLINE(603, "Decline"),
    DOES_NOT_EXIST_ANYWHERE(604, "Does not exist anywhere"),
    NOT_ACCEPTABLE_GLOBAL(606, "Not Acceptable")
    ;

    private int code;
    private String reason;

    public static StatusCode codeOf(int code) {
        return Arrays.stream(StatusCode.values())
                .filter(c -> c.getCode() == code)
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(StatusCode.class, String.valueOf(code)));
    }
}
