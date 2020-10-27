package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.common.ContentType;
import net.szvoc.xsip.sip.header.AcceptHeader;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeaderName;
import net.szvoc.xsip.sip.parser.internal.*;

@BindingHeaderName(HeaderName.ACCEPT)
public class AcceptParser extends Parser<AcceptHeader> {
    @Override
    protected AcceptHeader doParse(Lexer lexer) throws SyntaxException {
        AcceptHeader header = new AcceptHeader();
        while (true) {
            if (header.containsValue() && lexer.read(CharacterType.COMMA) == null) {
                break;
            }
            ContentType contentType = new ContentType();
            new ComplexToken(true, lexer, t -> header.add(contentType)) {
                @Override
                protected void rules() {
                    rule(new WordToken(true, lexer, contentType::setContentType));
                    rule(new CharacterToken(CharacterType.SLASH, true, lexer));
                    rule(new WordToken(true, lexer, contentType::setContentSubType));
                    rule(new ParametersToken(false, lexer, contentType::setParameters));
                }
            }.match();
        }
        return header;
    }
}
