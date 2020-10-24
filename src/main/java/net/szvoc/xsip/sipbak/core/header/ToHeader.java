package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Addr;
import net.szvoc.xsip.sipbak.core.Field;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.TO)
public class ToHeader extends ContactHeader {

    public ToHeader(Addr addr, List<Field> fields) {
        super(HeaderName.TO, addr, fields);
    }
}
