package net.szvoc.xsip.sip.header;

import lombok.Data;
import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.common.Method;

@Data
public class CSeq {
    private int sn;
    private EnumEx<Method> method;
}
