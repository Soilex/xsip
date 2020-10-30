package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.NumericToken;

import java.math.BigDecimal;

/**
 * Content-Length:
 * The Content-Length header field indicates the size of the message-
 * body, in decimal number of octets, sent to the recipient.
 * Applications SHOULD use this field to indicate the size of the
 * message-body to be transferred, regardless of the media type of the
 * entity.  If a stream-based protocol (such as TCP) is used as
 * transport, the header field MUST be used.
 * The size of the message-body does not include the CRLF separating
 * header fields and body.  Any Content-Length greater than or equal to
 * zero is a valid value.  If no body is present in a message, then the
 * Content-Length header field value MUST be set to zero.
 * The ability to omit Content-Length simplifies the creation of
 * cgi-like scripts that dynamically generate responses.
 * The compact form of the header field is l.
 * Example:
 * Content-Length: 349
 */
@BindingHeader({HeaderName.CONTENT_LENGTH, HeaderName.SESSION_EXPIRES, HeaderName.MIN_SE, HeaderName.MAX_FORWARDS, HeaderName.EXPIRES, HeaderName.MIN_EXPIRES, HeaderName.TIMESTAMP})
public class NumericParser extends Parser<BigDecimal> {
    @Override
    protected Header<BigDecimal> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException {
        Header<BigDecimal> header = new Header<>(headerName);
        new NumericToken(true, lexer, header::add).match();
        return header;
    }
}
