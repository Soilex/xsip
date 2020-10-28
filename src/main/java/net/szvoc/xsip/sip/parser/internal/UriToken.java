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
        return new ComplexToken(isRequired(), this.lexer, t -> this.setValue(uri))
                // schema
                .define(new ComplexToken(true, this.lexer)
                        .define(new WordToken(true, this.lexer, uri::setSchema))
                        .define(new CharacterToken(CharacterType.COLON, true, this.lexer)))
                // user
                .define(new ComplexToken(false, this.lexer)
                        .define(new WordToken(true, this.lexer, uri::setUser))
                        .define(new CharacterToken(CharacterType.ALT, true, this.lexer)))
                // host
                .define(new WordToken(true, this.lexer, uri::setHost))
                // port
                .define(new ComplexToken(false, this.lexer)
                        .define(new CharacterToken(CharacterType.COLON, true, this.lexer))
                        .define(new NumericToken(true, this.lexer, t -> uri.setPort(t.intValue())))
                )
                // parameters
                .define(new ParametersToken(false, this.lexer, uri::setParameters))
                .match();
    }
}
