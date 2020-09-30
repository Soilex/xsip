package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.Header;
import net.szvoc.callcenter.sip.HeaderName;
import net.szvoc.callcenter.sip.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.SESSION_EXPIRES)
public class SessionExpiresHeader extends Header {

    public SessionExpiresHeader(int value) {
        super(HeaderName.SESSION_EXPIRES, value + "");
    }
}
