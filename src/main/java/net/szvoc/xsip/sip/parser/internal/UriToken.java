package net.szvoc.xsip.sip.parser.internal;

import lombok.Getter;
import net.szvoc.xsip.sip.common.URI;
import net.szvoc.xsip.sip.parser.SyntaxException;

@BindingType(TokenType.URI)
public class UriToken extends Token {
    @Getter
    private URI value;

    protected UriToken(Lexer lexer) {
        super(TokenType.URI, lexer);
    }

    @Override
    protected void scan() throws SyntaxException {
        URI uri = new URI();
        WordToken schemaToken = lexer.nextToken(TokenType.WORD);
        uri.setSchema(schemaToken.getValue());
        if (!CharacterType.COLON.isMatch(lexer.read())) {
            lexer.throwSyntaxException();
        }
        WordToken userToken = lexer.nextToken(TokenType.WORD);
        WordToken hostToken = null;
        if (CharacterType.AT.isMatch(lexer.look())) {
            uri.setUser(userToken.getValue());
            hostToken = lexer.skip(1).nextToken(TokenType.WORD);
        } else {
            hostToken = userToken;
        }
        uri.setHost(hostToken.getValue());
        if (CharacterType.COLON.isMatch(lexer.look())) {
            NumericToken portToken = lexer.skip(1).nextToken(TokenType.NUMERIC);
            uri.setPort(portToken.getIn32());
        }
        while (CharacterType.SEMICOLON.isMatch(lexer.look())) {
            ParameterToken parameterToken = lexer.nextToken(TokenType.PARAMETER);
            uri.setParameter(parameterToken.getParameterName(), parameterToken.getParameterValue());
            lexer.skipBlank();
        }
        this.value = uri;
    }
}
