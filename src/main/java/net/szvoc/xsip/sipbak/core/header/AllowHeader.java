package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Header;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.ALLOW)
public class AllowHeader extends Header {
    private String[] methods = new String[0];

    public AllowHeader(String[] methods) {
        super(HeaderName.ALLOW, methods == null ? "" : String.join(", ", methods));
        this.methods = methods;
    }

    @Override
    protected void load(String nameAndValue) {
        super.load(nameAndValue);

        this.methods = value().split("\\s*,\\s*");
    }
}
