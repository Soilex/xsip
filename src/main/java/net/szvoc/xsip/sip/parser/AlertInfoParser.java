package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.StringToken;

/**
 * When present in an INVITE request, the Alert-Info header field
 * specifies an alternative ring tone to the UAS.  When present in a 180
 * (Ringing) response, the Alert-Info header field specifies an
 * alternative ringback tone to the UAC.  A typical usage is for a proxy
 * to insert this header field to provide a distinctive ring feature.
 * <p>
 * The Alert-Info header field can introduce security risks.  These
 * risks and the ways to handle them are discussed in Section 20.9,
 * which discusses the Call-Info header field since the risks are
 * identical.
 * <p>
 * In addition, a user SHOULD be able to disable this feature
 * selectively.
 * <p>
 * This helps prevent disruptions that could result from the use of
 * this header field by untrusted elements.
 * <p>
 * Example:
 * Alert-Info: <http://www.example.com/sounds/moo.wav>
 */
@BindingHeader(HeaderName.ALERT_INFO)
public class AlertInfoParser extends Parser<String> {
    @Override
    protected Header<String> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<String> header = new Header<>(headerName);
        new StringToken(true, lexer, header::add)
                .startWith(CharacterType.LESS_THAN)
                .endWith(CharacterType.GREATER_THAN)
                .match();
        return header;
    }
}
