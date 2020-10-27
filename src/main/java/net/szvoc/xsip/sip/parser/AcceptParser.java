package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.AcceptHeader;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeaderName;
import net.szvoc.xsip.sip.parser.internal.Lexer;

@BindingHeaderName(HeaderName.ACCEPT)
public class AcceptParser extends Parser<AcceptHeader> {
    @Override
    protected AcceptHeader doParse(Lexer lexer) throws SyntaxException {
        AcceptHeader header = new AcceptHeader();
//        boolean once = true;
//        while (!lexer.isEndOfLine()) {
//            if (!once && !Character.COMMA.isMatch(lexer.read())) {
//                lexer.throwSyntaxException();
//            }
//            lexer.skipBlank();
//            WordToken typeToken = lexer.nextToken(TokenType.WORD);
//            String type = typeToken.getValue().get();
//            if (!Character.SLASH.isMatch(lexer.read())) {
//                lexer.throwSyntaxException();
//            }
//            WordToken subTypeToken = lexer.nextToken(TokenType.WORD);
//            String subType = subTypeToken.getValue().get();
//            AcceptHeader.ContentType contentType = new AcceptHeader.ContentType();
//            contentType.setContentType(type);
//            contentType.setContentSubType(subType);
//            header.add(contentType);
//            while (Character.SEMICOLON.isMatch(lexer.look())) {
//                ParameterToken parameterToken = lexer.nextToken(TokenType.PARAMETER);
//                contentType.setParameter(parameterToken.getValue().get());
//                lexer.skipBlank();
//            }
//            once = false;
//        }
        return header;
    }
}
