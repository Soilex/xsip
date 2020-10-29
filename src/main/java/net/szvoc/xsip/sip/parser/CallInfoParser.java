package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.CallInfo;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.CharacterToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.StringToken;
import net.szvoc.xsip.sip.parser.internal.WordToken;

/**
 * The Call-Info header field provides additional information about the
 * caller or callee, depending on whether it is found in a request or
 * response.  The purpose of the URI is described by the "purpose"
 * parameter.  The "icon" parameter designates an image suitable as an
 * iconic representation of the caller or callee.  The "info" parameter
 * describes the caller or callee in general, for example, through a web
 * page.  The "card" parameter provides a business card, for example, in
 * vCard [36] or LDIF [37] formats.  Additional tokens can be registered
 * using IANA and the procedures in Section 27.
 * Use of the Call-Info header field can pose a security risk.  If a
 * callee fetches the URIs provided by a malicious caller, the callee
 * may be at risk for displaying inappropriate or offensive content,
 * dangerous or illegal content, and so on.  Therefore, it is
 * RECOMMENDED that a UA only render the information in the Call-Info
 * header field if it can verify the authenticity of the element that
 * originated the header field and trusts that element.  This need not
 * be the peer UA; a proxy can insert this header field into requests.
 * Example:
 * Call-Info: <http://wwww.example.com/alice/photo.jpg>;purpose=icon, <http://www.example.com/alice/>;purpose=info
 */
@BindingHeader(HeaderName.CALL_INFO)
public class CallInfoParser extends Parser<CallInfo> {
    @Override
    protected Header<CallInfo> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<CallInfo> header = new Header<>(headerName);
        resolve(header, lexer, () -> {
            CallInfo callInfo = new CallInfo();
            new StringToken(true, lexer, callInfo::setUri)
                    .startWith(CharacterType.LESS_THAN)
                    .endWith(CharacterType.GREATER_THAN)
                    .match();
            new CharacterToken(true, lexer).expect(CharacterType.SEMICOLON).match();
            new WordToken(true, lexer)
                    .filter((token, tokenValue) -> tokenValue.equalsIgnoreCase("purpose"))
                    .match();
            new CharacterToken(true, lexer).expect(CharacterType.EQUALS).match();
            new WordToken(true, lexer, callInfo::setPurpose).match();
            header.add(callInfo);
        });
        return header;
    }
}
