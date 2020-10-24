package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Header;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.SUPPORTED)
public class SupportedHeader extends Header {
    private String[] abilities = new String[0];

    public SupportedHeader(String[] abilities) {
        super(HeaderName.SUPPORTED, abilities == null ? "" : String.join(", ", abilities));
        this.abilities = abilities;
    }

    @Override
    protected void load(String nameAndValue) {
        super.load(nameAndValue);
        this.abilities = value().split("\\s*,\\s*");
    }
}