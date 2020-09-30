package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.Header;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnknownHeader extends Header {

    public UnknownHeader(String name, String value) {
        super(name, value);
    }
}
