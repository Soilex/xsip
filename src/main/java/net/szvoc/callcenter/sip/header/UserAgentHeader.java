package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.Header;
import net.szvoc.callcenter.sip.HeaderName;
import net.szvoc.callcenter.sip.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.USER_AGENT)
public class UserAgentHeader extends Header {

    public UserAgentHeader(String value) {
        super(HeaderName.USER_AGENT, value);
    }
}
