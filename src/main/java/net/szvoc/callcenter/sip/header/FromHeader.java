package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.callcenter.sip.Addr;
import net.szvoc.callcenter.sip.Field;
import net.szvoc.callcenter.sip.HeaderName;
import net.szvoc.callcenter.sip.annotation.SipHeader;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.FROM)
public class FromHeader extends ContactHeader {

    public FromHeader(Addr addr, List<Field> fields) {
        super(HeaderName.FROM, addr, fields);
    }
}
