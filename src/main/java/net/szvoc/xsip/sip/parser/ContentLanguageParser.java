package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.CharacterToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.WordToken;

import java.util.Locale;

/**
 * The Content-Language header field is used to indicate the language of the
 * message body.
 * Example:
 * Content-Language: zh-CN
 */
@BindingHeader(HeaderName.CONTENT_LANGUAGE)
public class ContentLanguageParser extends Parser<Locale> {
    @Override
    protected Header<Locale> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<Locale> header = new Header<>(headerName);
        Locale.Builder builder = new Locale.Builder();
        new WordToken(true, lexer, builder::setLanguage).unexpect(CharacterType.MINUS).match();
        new CharacterToken(false, lexer).expect(CharacterType.MINUS).match();
        new WordToken(false, lexer, builder::setRegion).match();
        header.add(builder.build());
        return header;
    }
}
