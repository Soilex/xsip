package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.Addr;
import net.szvoc.callcenter.sip.Field;
import net.szvoc.callcenter.sip.HeaderName;
import net.szvoc.callcenter.sip.annotation.SipHeader;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.TO)
public class ToHeader extends ContactHeader {

    public ToHeader(Addr addr, List<Field> fields) {
        super(HeaderName.TO, addr, fields);
    }
}
