package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.common.Method;
import net.szvoc.xsip.sip.header.CSeq;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.internal.EnumToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.NumericToken;

public class CSeqParser extends Parser<CSeq> {
    @Override
    protected Header<CSeq> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException {
        Header<CSeq> header = new Header<>(headerName);
        CSeq cSeq = new CSeq();
        new NumericToken(true, lexer, t->cSeq.setSn(t.intValue())).match();
        new EnumToken<>(Method.UNKNOWN, true, lexer, cSeq::setMethod).match();
        header.add(cSeq);
        return header;
    }
}
