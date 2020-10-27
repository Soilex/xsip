package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.ContentType;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.*;

@BindingHeader(HeaderName.ACCEPT)
public class AcceptParser extends Parser<ContentType> {
    @Override
    protected Header<ContentType> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<ContentType> header = new Header<>(headerName);
        while (true) {
            if (header.containsValue() && lexer.read(CharacterType.COMMA) == null) {
                break;
            }
            ContentType contentType = new ContentType();
            new ComplexToken(true, lexer, t -> header.add(contentType)) {
                @Override
                protected void rules() {
                    rule(new WordToken(true, lexer, contentType::setMainType));
                    rule(new CharacterToken(CharacterType.SLASH, true, lexer));
                    rule(new WordToken(true, lexer, contentType::setSubType));
                    rule(new ParametersToken(false, lexer, contentType::setParameters));
                }
            }.match();
        }
        return header;
    }
}
