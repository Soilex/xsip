package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Header;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.EXPIRES)
public class ExpiresHeader extends Header {

    public ExpiresHeader(int value) {
        super(HeaderName.EXPIRES, value + "");
    }
}
