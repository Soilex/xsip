package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.WordToken;

/**
 * Call-ID:
 * The Call-ID header field uniquely identifies a particular invitation
 * or all registrations of a particular client.  A single multimedia
 * conference can give rise to several calls with different Call-IDs,
 * for example, if a user invites a single individual several times to
 * the same (long-running) conference.  Call-IDs are case-sensitive and
 * are simply compared byte-by-byte.
 * <p>
 * Examples:
 * Call-ID: f81d4fae-7dec-11d0-a765-00a0c91e6bf6@biloxi.com
 */
@BindingHeader(HeaderName.CALL_ID)
public class StringParser extends Parser<String> {
    @Override
    protected Header<String> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<String> header = new Header<>(headerName);
        new WordToken(true, lexer, header::add).match();
        return header;
    }
}
