package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.Header;
import net.szvoc.callcenter.sip.HeaderName;
import net.szvoc.callcenter.sip.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.MAX_FORWARDS)
public class MaxForwardsHeader extends Header {

    public MaxForwardsHeader(int value) {
        super(HeaderName.MAX_FORWARDS, value + "");
    }
}
