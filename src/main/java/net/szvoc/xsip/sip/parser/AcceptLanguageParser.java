package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.WordToken;

@BindingHeader(HeaderName.ACCEPT_LANGUAGE)
public class AcceptLanguageParser extends Parser<String> {
    @Override
    protected Header<String> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<String> header = new Header<>(headerName);
        while (true) {
            if (header.containsValue() && lexer.read(CharacterType.COMMA) == null) {
                break;
            }
            new WordToken(true, lexer, t -> header.add(t)).match();
        }
        return header;
    }
}
