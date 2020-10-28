package net.szvoc.xsip.sip.header;

public interface HeaderName {
    String EMPTY_LINE = ""; // 空行
    // RFC 3261
    String ACCEPT = "Accept";
    String ACCEPT_ENCODING = "Accept-Encoding";
    String ACCEPT_LANGUAGE = "Accept-Language";
    String ALTER_INFO = "Alter-Info";
    String ALLOW = "Allow";
    String AUTHENTICATION_INFO = "Authentication-Info";
    String AUTHORIZATION = "Authorization";
    String CALL_ID = "Call-ID";
    String CALL_INFO = "Call-Info";
    String CONTACT = "Contact";
    String CONTENT_DISPOSITION = "Content_Disposition";
    String CONTENT_ENCODING = "Content-Encoding";
    String CONTENT_LANGUAGE = "Content-Language";
    String CONTENT_LENGTH = "Content-Length";
    String CONTENT_TYPE = "Content-TYPE";
    String CSEQ = "CSeq";
    String DATE = "DATE";
    String ERROR_INFO = "Error-Info";
    String EXPIRES = "Expires";
    String FROM = "From";
    String IN_REPLY_TO = "In-Reply-To";
    String MAX_FORWARDS = "Max-Forwards";
    String MIN_EXPIRES = "Min-Expires";
    String MIME_VERSION = "MIME-Version";
    String ORGANIZATION = "Organization";
    String PRIORITY = "Priority";
    String PROXY_AUTHENTICATE = "Proxy-Authenticate";
    String PROXY_AUTHORIZATION = "Proxy-Authorization";
    String PROXY_REQUIRE = "Proxy-Require";
    String RECORD_ROUTE = "Record-Route";
    String REPLY_TO = "Reply-To";
    String REQUIRE = "Require";
    String RETRY_AFTER = "Retry-After";
    String ROUTE = "Route";
    String SERVER = "Server";
    String SUBJECT = "Subject";
    String SUPPORTED = "Supported";
    String TIMESTAMP = "Timestamp";
    String TO = "To";
    String UNSUPPORTED = "Unsupported";
    String USER_AGENT = "User-Agent";
    String VIA = "Via";
    String WARNING = "Warning";
    String WWW_AUTHENTICATE = "WWW-Authenticate";
    // RFC 3262
    String RACK = "RAck";
    String RSEQ = "RSeq";
    // RFC 3265
    String EVENT = "EVENT";
    String ALLOW_EVENTS = "Allow-Events";
    String SUBSCRIPTION_STATE = "Subscription-State";
    // RFC 3326
    String REASON = "Reason";
    // RFC 3891
    String Replaces = "Replaces";
    // RFC 3892
    String ReferredBy = "ReferredBy";
    // RFC 3903
    String SIP_ETAG = "SIP-ETag";
    String SIP_IF_MATCH = "SIP-If-Match";
    // RFC 3911
    String JOIN = "Join";
    // RFC 4028
    String MIN_SE = "Min-SE";
    String SESSION_EXPIRES = "Session-Expires";
}
