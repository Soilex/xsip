package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.header.AcceptLanguage;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.*;

/**
 * The Accept-Language header field is used in requests to indicate the
 * preferred languages for reason phrases, session descriptions, or
 * status responses carried as message bodies in the response.  If no
 * Accept-Language header field is present, the server SHOULD assume all
 * languages are acceptable to the client.
 * The Accept-Language header field follows the syntax defined in
 * [H14.4].  The rules for ordering the languages based on the "q"
 * parameter apply to SIP as well.
 * Example:
 * Accept-Language: da, en-gb;q=0.8, en;q=0.7
 */
@BindingHeader(HeaderName.ACCEPT_LANGUAGE)
public class AcceptLanguageParser extends Parser<AcceptLanguage> {
    @Override
    protected Header<AcceptLanguage> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException {
        Header<AcceptLanguage> header = new Header<>(headerName);
        resolve(header, lexer, () -> {
            AcceptLanguage acceptLanguage = new AcceptLanguage();
            new ComplexToken(true, lexer, t -> header.add(acceptLanguage))
                    .define(new WordToken(true, lexer, acceptLanguage::setLanguage).unexpect(CharacterType.MINUS))
                    .define(new CharacterToken(false, lexer).expect(CharacterType.MINUS))
                    .define(new WordToken(false, lexer, acceptLanguage::setCountry))
                    .define(new ParametersToken(false, lexer, acceptLanguage::setParameters))
                    .match();
        });
        return header;
    }
}
