package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.URI;

public class UriToken extends ComplexToken<URI> {
    public UriToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected URI defaultValue() {
        return new URI();
    }

    @Override
    protected void rules(Holder<URI> uriHolder) {
        URI uri = uriHolder.getValue();
        // schema
        rule(new ComplexToken<String>(true, this.lexer) {
            @Override
            protected void rules(Holder<String> holder) {
                rule(new WordToken(true, this.lexer))
                        .thenAccept(result -> uri.setSchema(result.getValue().get()));
                rule(new CharacterToken(Character.COLON, true, this.lexer));
            }
        });
        // user
        rule(new ComplexToken<String>(false, this.lexer) {
            @Override
            protected void rules(Holder<String> holder) {
                rule(new WordToken(true, this.lexer))
                        .thenAccept(result -> uri.setUser(result.getValue().get()));
                rule(new CharacterToken(Character.ALT, true, this.lexer));
            }
        });
        // host
        rule(new WordToken(true, this.lexer))
                .thenAccept(result -> uri.setHost(result.getValue().get()));
        // port
        rule(new ComplexToken<String>(false, this.lexer) {
            @Override
            protected void rules(Holder<String> holder) {
                rule(new CharacterToken(Character.COLON, true, this.lexer));
                rule(new NumericToken(true, this.lexer))
                        .thenAccept(result -> uri.setPort(result.getValue().get().intValue()));
            }
        });
        // parameters
        rule(new ParametersToken(false, this.lexer))
                .thenAccept(result -> {
//                    if (result.getValue().isPresent()) {
                        result.getValue().get().forEach(uri::setParameter);
//                    }
                });
    }
}
