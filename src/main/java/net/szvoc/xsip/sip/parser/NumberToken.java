package net.szvoc.xsip.sip.parser;

import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

public class NumberToken extends Token {
    public NumberToken(Lexer lexer) {
        super(TokenType.NUMBER, lexer);
    }

    public int getIn32() {
        return Integer.parseInt(super.getTokenValue());
    }

    public long getInt64() {
        return Long.parseLong(super.getTokenValue());
    }

    public float getFloat() {
        return Float.parseFloat(super.getTokenValue());
    }

    @Override
    protected void scan() {
        lexer.skipBlank();
        StringBuilder stringBuilder = new StringBuilder();
        while (!lexer.isEOF()) {
            char ch = lexer.pick();
            if (!CharacterType.DIGIT.isMatch(ch)) {
                lexer.back();
                break;
            }
            stringBuilder.append(ch);
        }
        if (stringBuilder.length() == 0) {
            throw new SyntaxException();
        }
    }
}
