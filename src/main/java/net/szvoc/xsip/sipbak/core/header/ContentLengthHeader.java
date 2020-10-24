package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Header;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.CONTENT_LENGTH)
public class ContentLengthHeader extends Header {

    public ContentLengthHeader(int value) {
        super(HeaderName.CONTENT_LENGTH, value + "");
    }
}
