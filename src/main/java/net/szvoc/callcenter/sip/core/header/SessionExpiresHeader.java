package net.szvoc.callcenter.sip.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.core.Header;
import net.szvoc.callcenter.sip.core.HeaderName;
import net.szvoc.callcenter.sip.core.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.SESSION_EXPIRES)
public class SessionExpiresHeader extends Header {

    public SessionExpiresHeader(int value) {
        super(HeaderName.SESSION_EXPIRES, value + "");
    }
}
