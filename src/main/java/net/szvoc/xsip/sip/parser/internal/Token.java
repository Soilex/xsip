package net.szvoc.xsip.sip.parser.internal;

import lombok.Getter;
import net.szvoc.xsip.sip.parser.SyntaxException;

public abstract class Token {
    @Getter
    private TokenType tokenType;

    protected Lexer lexer;

    protected Token(TokenType tokenType, Lexer lexer) {
        this.tokenType = tokenType;
        this.lexer = lexer;
    }

    protected abstract void scan() throws SyntaxException;
}
