package net.szvoc.xsip.sip.parser.internal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


public abstract class Token {
    @Getter
    private TokenType tokenType;

    protected Lexer lexer;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private String tokenValue;

    protected Token(TokenType tokenType, Lexer lexer) {
        this.tokenType = tokenType;
        this.lexer = lexer;
    }

    protected abstract void scan() throws SyntaxException;
}
