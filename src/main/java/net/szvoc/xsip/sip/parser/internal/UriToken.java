package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.URI;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

@BindingTokenType(TokenType.URI)
public class UriToken extends ComplexToken<URI> {
    public UriToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    public void scan() throws SyntaxException {
        URI uri = new URI();
        // parse schema
        definition(new ComplexToken<String>(true, this.lexer) {
            @Override
            public void scan() throws SyntaxException {
                definition(new WordToken(true, this.lexer)).thenAccept(result -> uri.setSchema(result.getValue()));
                definition(new CharacterToken(Character.COLON, true, this.lexer));
                super.scan();
            }
        });
        // parse user
        definition(new ComplexToken<String>(false, this.lexer) {
            @Override
            public void scan() throws SyntaxException {
                definition(new WordToken(true, this.lexer)).thenAccept(result -> uri.setUser(result.getValue()));
                definition(new CharacterToken(Character.ALT, true, this.lexer));
                super.scan();
            }
        });
        // parse host
        definition(new WordToken(true, this.lexer)).thenAccept(result -> uri.setHost(result.getValue()));
        // parse port
        definition(new ComplexToken<String>(false, this.lexer) {
            @Override
            public void scan() throws SyntaxException {
                definition(new CharacterToken(Character.COLON, true, this.lexer));
                definition(new NumericToken(true, this.lexer)).thenAccept(result -> uri.setPort(result.getValue().intValue()));
                super.scan();
            }
        });
        super.scan();
        // parse parameters
        while (Character.SEMICOLON.isMatch(lexer.look())) {
            ParameterToken parameterToken = new ParameterToken(true, this.lexer);
            uri.setParameter(parameterToken.getValue().get());
        }
        this.setValue(uri);
    }
}
