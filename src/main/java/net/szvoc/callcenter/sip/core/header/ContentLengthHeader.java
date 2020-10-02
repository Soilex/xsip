package net.szvoc.callcenter.sip.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.core.Header;
import net.szvoc.callcenter.sip.core.HeaderName;
import net.szvoc.callcenter.sip.core.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.CONTENT_LENGTH)
public class ContentLengthHeader extends Header {

    public ContentLengthHeader(int value) {
        super(HeaderName.CONTENT_LENGTH, value + "");
    }
}
