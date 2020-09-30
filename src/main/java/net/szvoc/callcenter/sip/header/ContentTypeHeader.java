package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.Header;
import net.szvoc.callcenter.sip.HeaderName;
import net.szvoc.callcenter.sip.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.CONTENT_TYPE)
public class ContentTypeHeader extends Header {

    public ContentTypeHeader(String value) {
        super(HeaderName.CONTENT_TYPE, value);
    }
}
