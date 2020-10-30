package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.common.Method;
import net.szvoc.xsip.sip.header.CSeq;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.EnumToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.NumericToken;

/**
 * A CSeq header field in a request contains a single decimal sequence
 * number and the request method.  The sequence number MUST be
 * expressible as a 32-bit unsigned integer.  The method part of CSeq is
 * case-sensitive.  The CSeq header field serves to order transactions
 * within a dialog, to provide a means to uniquely identify
 * transactions, and to differentiate between new requests and request
 * retransmissions.  Two CSeq header fields are considered equal if the
 * sequence number and the request method are identical.
 * Example:
 * CSeq: 4711 INVITE
 */
@BindingHeader(HeaderName.CSEQ)
public class CSeqParser extends Parser<CSeq> {
    @Override
    protected Header<CSeq> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException {
        Header<CSeq> header = new Header<>(headerName);
        CSeq cSeq = new CSeq();
        new NumericToken(true, lexer, t -> cSeq.setSn(t.intValue())).match();
        new EnumToken<>(Method.UNKNOWN, true, lexer, cSeq::setMethod).match();
        header.add(cSeq);
        return header;
    }
}
