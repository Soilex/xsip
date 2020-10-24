package net.szvoc.xsip.sip.parser.internal;

import lombok.Getter;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

@BindingTokenType(TokenType.PARAMETER)
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
        if (CharacterType.SEMICOLON.isMatch(lexer.read())) {
            WordToken parameterNameToken = lexer.nextToken(TokenType.WORD);
            parameterName = parameterNameToken.getValue();
            if (CharacterType.EQUALS.isMatch(lexer.look())) {
                lexer.skip(1);
                WordToken parameterValueToken = lexer.nextToken(TokenType.WORD);
                parameterValue = parameterValueToken.getValue();
            }
        } else {
            lexer.throwSyntaxException();
        }
    }
}
