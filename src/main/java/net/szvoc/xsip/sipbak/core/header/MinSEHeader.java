package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Header;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.MIN_SE)
public class MinSEHeader extends Header {

    public MinSEHeader(int value) {
        super(HeaderName.MIN_SE, value + "");
    }
}
