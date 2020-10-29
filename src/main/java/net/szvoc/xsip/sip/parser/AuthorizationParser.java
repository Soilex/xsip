package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.Authorization;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.ParametersToken;
import net.szvoc.xsip.sip.parser.internal.WordToken;

/**
 * The Authorization header field contains authentication credentials of
 * a UA.  Section 22.2 overviews the use of the Authorization header
 * field, and Section 22.4 describes the syntax and semantics when used
 * with HTTP authentication.
 * <p>
 * This header field, along with Proxy-Authorization, breaks the general
 * rules about multiple header field values.  Although not a comma-
 * separated list, this header field name may be present multiple times,
 * and MUST NOT be combined into a single header line using the usual
 * rules described in Section 7.3.
 * <p>
 * Example:
 * Authorization: Digest username="Alice", realm="atlanta.com", nonce="84a4cc6f3082121f32b42a2187831a9e", response="7587245234b3434cc3412213e5f113a5432"
 */
@BindingHeader(HeaderName.AUTHORIZATION)
public class AuthorizationParser extends Parser<Authorization> {
    @Override
    protected Header<Authorization> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<Authorization> header = new Header<>(headerName);
        Authorization authorization = new Authorization();
        new WordToken(true, lexer, authorization::setScheme).match();
        new ParametersToken(true, lexer, authorization::setParameters)
                .delimiter(CharacterType.COMMA)
                .ignoreFirstDelimiter()
                .match();
        header.add(authorization);
        return header;
    }
}
