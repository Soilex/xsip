package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.common.Method;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.EnumToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;

/**
 * The Allow header field lists the set of methods supported by the UA
 * generating the message.
 *
 * All methods, including ACK and CANCEL, understood by the UA MUST be
 * included in the list of methods in the Allow header field, when
 * present.  The absence of an Allow header field MUST NOT be
 * interpreted to mean that the UA sending the message supports no
 * methods.   Rather, it implies that the UA is not providing any
 * information on what methods it supports.
 *
 * Supplying an Allow header field in responses to methods other than
 * OPTIONS reduces the number of messages needed.
 *
 * Example:
 *    Allow: INVITE, ACK, OPTIONS, CANCEL, BYE
 */
@BindingHeader(HeaderName.ALLOW)
public class AllowParser extends Parser<EnumEx<Method>> {
    @Override
    protected Header<EnumEx<Method>> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<EnumEx<Method>> header = new Header<>(headerName);
        resolve(header, lexer, ()->new EnumToken<Method>( Method.UNKNOWN, true, lexer, header::add).match());
        return header;
    }
}
