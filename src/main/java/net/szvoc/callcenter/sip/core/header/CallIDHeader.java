package net.szvoc.callcenter.sip.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.core.Header;
import net.szvoc.callcenter.sip.core.HeaderName;
import net.szvoc.callcenter.sip.core.annotation.SipHeader;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.CALL_ID)
public class CallIDHeader extends Header {

    public CallIDHeader(String value) {
        super(HeaderName.CALL_ID, value);
    }
}
