package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Header;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.USER_AGENT)
public class UserAgentHeader extends Header {

    public UserAgentHeader(String value) {
        super(HeaderName.USER_AGENT, value);
    }
}
