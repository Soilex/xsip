package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.AcceptEncoding;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.ComplexToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.ParametersToken;
import net.szvoc.xsip.sip.parser.internal.WordToken;

/**
 * The Accept-Encoding header field is similar to Accept, but restricts
 * the content-codings [H3.5] that are acceptable in the response.  See
 * [H14.3].  The semantics in SIP are identical to those defined in
 * [H14.3].
 *
 * An empty Accept-Encoding header field is permissible.  It is
 * equivalent to Accept-Encoding: identity, that is, only the identity
 * encoding, meaning no encoding, is permissible.
 *
 * If no Accept-Encoding header field is present, the server SHOULD
 * assume a default value of identity.
 *
 * This differs slightly from the HTTP definition, which indicates that
 * when not present, any encoding can be used, but the identity encoding
 * is preferred.
 *
 * Example:
 *    Accept-Encoding: deflate, gzip;q=1.0, *;q=0.5
 */
@BindingHeader(HeaderName.ACCEPT_ENCODING)
public class AcceptEncodingParser extends Parser<AcceptEncoding> {
    @Override
    protected Header<AcceptEncoding> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<AcceptEncoding> header = new Header<>(headerName);
        resolve(header, lexer, () -> {
            AcceptEncoding acceptEncoding = new AcceptEncoding();
            new ComplexToken(true, lexer, t -> header.add(acceptEncoding))
                    .define(new WordToken(true, lexer, acceptEncoding::setEncoding)
                            .filter((token, tokenValue) -> tokenValue.equals("*") || !tokenValue.startsWith("*")))
                    .define(new ParametersToken(false, lexer, acceptEncoding::setParameters))
                    .match();
        });
        return header;
    }
}
