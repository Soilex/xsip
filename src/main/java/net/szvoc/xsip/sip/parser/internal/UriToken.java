package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.URI;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public class UriToken extends Token<URI> {
    public UriToken(String id, boolean required, Lexer lexer, Consumer<URI> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public UriToken(boolean required, Lexer lexer, Consumer<URI> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public UriToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public UriToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected void doMatch() throws SyntaxException {
        final URI uri = new URI();
        new ComplexToken(isRequired(), this.lexer, t -> this.setValue(uri)) {
            @Override
            protected void rules() {
                // schema
                rule(new ComplexToken(true, this.lexer) {
                    @Override
                    protected void rules() {
                        rule(new WordToken(true, this.lexer, uri::setSchema));
                        rule(new CharacterToken(Character.COLON, true, this.lexer));
                    }
                });
                // user
                rule(new ComplexToken(false, this.lexer) {
                    @Override
                    protected void rules() {
                        rule(new WordToken(true, this.lexer, uri::setUser));
                        rule(new CharacterToken(Character.ALT, true, this.lexer));
                    }
                });
                // host
                rule(new WordToken(true, this.lexer, uri::setHost));
                // port
                rule(new ComplexToken(false, this.lexer) {
                    @Override
                    protected void rules() {
                        rule(new CharacterToken(Character.COLON, true, this.lexer));
                        rule(new NumericToken(true, this.lexer, t -> uri.setPort(t.intValue())));
                    }
                });
                // parameters
                rule(new ParametersToken(false, this.lexer, t -> t.forEach(uri::setParameter)));
            }
        }.match();
    }
}
