package net.szvoc.callcenter.sip.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.core.Addr;
import net.szvoc.callcenter.sip.core.Field;
import net.szvoc.callcenter.sip.core.HeaderName;
import net.szvoc.callcenter.sip.core.annotation.SipHeader;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.FROM)
public class FromHeader extends ContactHeader {

    public FromHeader(Addr addr, List<Field> fields) {
        super(HeaderName.FROM, addr, fields);
    }
}
