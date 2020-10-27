package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
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
    protected boolean doMatch() throws SyntaxException {
        final URI uri = new URI();
        return new ComplexToken(isRequired(), this.lexer, t -> this.setValue(uri)) {
            @Override
            protected void rules() {
                // schema
                rule(new ComplexToken(true, this.lexer) {
                    @Override
                    protected void rules() {
                        rule(new WordToken(true, this.lexer, uri::setSchema));
                        rule(new CharacterToken(CharacterType.COLON, true, this.lexer));
                    }
                });
                // user
                rule(new ComplexToken(false, this.lexer) {
                    @Override
                    protected void rules() {
                        rule(new WordToken(true, this.lexer, uri::setUser));
                        rule(new CharacterToken(CharacterType.ALT, true, this.lexer));
                    }
                });
                // host
                rule(new WordToken(true, this.lexer, uri::setHost));
                // port
                rule(new ComplexToken(false, this.lexer) {
                    @Override
                    protected void rules() {
                        rule(new CharacterToken(CharacterType.COLON, true, this.lexer));
                        rule(new NumericToken(true, this.lexer, t -> uri.setPort(t.intValue())));
                    }
                });
                // parameters
                rule(new ParametersToken(false, this.lexer, uri::setParameters));
            }
        }.match();
    }
}
