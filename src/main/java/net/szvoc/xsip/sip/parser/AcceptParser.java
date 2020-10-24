package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.AcceptHeader;
import net.szvoc.xsip.sip.parser.internal.*;

public class AcceptParser extends Parser<AcceptHeader> {
    private static final String Q_VALUE = "q-value";

    public AcceptParser() {
        super(DELIMITER_COLON);
    }

    @Override
    protected AcceptHeader parse(Lexer lexer) throws SyntaxException {
        AcceptHeader header = new AcceptHeader();
        boolean isFirst = true;
        while (!lexer.isEndOfLine()) {
            if (!isFirst && !CharacterType.COMMA.isMatch(lexer.read())) {
                lexer.throwSyntaxException();
            }
            lexer.skipBlank();
            WordToken typeToken = lexer.nextToken(TokenType.WORD);
            String type = typeToken.getTokenValue();
            if (!CharacterType.SLASH.isMatch(lexer.read())) {
                lexer.throwSyntaxException();
            }
            WordToken subTypeToken = lexer.nextToken(TokenType.WORD);
            String subType = subTypeToken.getTokenValue();
            AcceptHeader.ContentType contentType = new AcceptHeader.ContentType();
            contentType.setContentType(type);
            contentType.setContentSubType(subType);
            contentType.setQValue(1f);
            header.add(contentType);
            while (CharacterType.SEMICOLON.isMatch(lexer.look())) {
                ParameterToken parameterToken = lexer.nextToken(TokenType.PARAMETER);
                if (parameterToken.getParameterName().equals(Q_VALUE)) {
                    try {
                        float qValue = Float.parseFloat(parameterToken.getParameterValue());
                        contentType.setQValue(qValue);
                    } catch (NumberFormatException ignore) {
                        lexer.throwSyntaxException();
                    }
                } else {
                    contentType.setParameter(parameterToken.getParameterName(), parameterToken.getParameterValue());
                }
                lexer.skipBlank();
            }
            isFirst = false;
        }
        return header;
    }
}
