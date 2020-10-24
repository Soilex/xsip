package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Header;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnknownHeader extends Header {

    public UnknownHeader(String name, String value) {
        super(name, value);
    }
}
