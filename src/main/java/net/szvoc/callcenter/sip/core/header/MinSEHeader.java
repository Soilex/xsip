package net.szvoc.callcenter.sip.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.core.Header;
import net.szvoc.callcenter.sip.core.HeaderName;
import net.szvoc.callcenter.sip.core.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.MIN_SE)
public class MinSEHeader extends Header {

    public MinSEHeader(int value) {
        super(HeaderName.MIN_SE, value + "");
    }
}
