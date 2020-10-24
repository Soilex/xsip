package net.szvoc.xsip.sip.parser.internal;

import lombok.Getter;

public class ParameterToken extends Token {
    @Getter
    private String parameterName;

    @Getter
    private String parameterValue;

    protected ParameterToken(Lexer lexer) {
        super(TokenType.PARAMETER, lexer);
    }

    @Override
    protected void scan() throws SyntaxException {
        lexer.skipBlank();
        if (CharacterType.SEMICOLON.isMatch(lexer.read())) {
            WordToken parameterNameToken = lexer.nextToken(TokenType.WORD);
            parameterName = parameterNameToken.getTokenValue();
            if (CharacterType.EQUALS.isMatch(lexer.read())) {
                WordToken parameterValueToken = lexer.nextToken(TokenType.WORD);
                parameterValue = parameterValueToken.getTokenValue();
            }
        } else {
            lexer.throwSyntaxException();
        }
    }
}
