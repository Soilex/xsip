package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.DateToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;

import java.time.ZonedDateTime;

/**
 * Implements a parser class for tracking expiration time
 * when specified as a Date value.
 * From the HTTP 1.1 spec
 * The Date general-header field represents the date and time at which
 * the message was originated, having the same semantics as orig-date in
 * RFC 822. The field value is an HTTP-date, as described in section
 * 3.3.1; it MUST be sent in RFC 1123 [8]-date format.
 * Date  = "Date" ":" HTTP-date
 * Example:
 * Date: Tue, 15 Nov 1994 08:12:31 GMT
 */
@BindingHeader({HeaderName.DATE})
public class DateParser extends Parser<ZonedDateTime> {
    @Override
    protected Header<ZonedDateTime> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException {
        Header<ZonedDateTime> header = new Header<>(headerName);
        resolve(header, lexer, () -> new DateToken(true, lexer, header::add).match());
        return header;
    }
}
