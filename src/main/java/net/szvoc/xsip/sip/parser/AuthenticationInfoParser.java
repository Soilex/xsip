package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.header.AuthenticationInfo;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.ParametersToken;

/**
 * The Authentication-Info header field provides for mutual
 * authentication with HTTP Digest.  A UAS MAY include this header field
 * in a 2xx response to a request that was successfully authenticated
 * using digest based on the Authorization header field.
 * Syntax and semantics follow those specified in RFC 2617 [17].
 * Example:
 * Authentication-Info: nextnonce="47364c23432d2e131a5fb210812c"
 */
@BindingHeader(HeaderName.AUTHENTICATION_INFO)
public class AuthenticationInfoParser extends Parser<AuthenticationInfo> {
    @Override
    protected Header<AuthenticationInfo> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException {
        Header<AuthenticationInfo> header = new Header<>(headerName);
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        new ParametersToken(true, lexer, authenticationInfo::setParameters)
                .delimiter(CharacterType.COMMA)
                .ignoreFirstDelimiter()
                .match();
        header.add(authenticationInfo);
        return header;
    }
}
