package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;

public class Lexer {
    private String source;
    private int position = 0;

    private static final char CR = '\r';
    private static final char LF = '\n';

    public Lexer(String source) {
        this.source = source;
    }

    public Character look() {
        if (isEOF()) {
            return null;
        }
        return source.charAt(position);
    }

    public Character read() {
        if (isEOF()) {
            return null;
        }
        return source.charAt(position++);
    }

    public Lexer skip(int offset) {
        position += offset;
        return this;
    }

    public Lexer back() {
        position--;
        return this;
    }

    public <T extends Token> T nextToken(TokenType tokenType) throws SyntaxException {
        Token token = TokenFactory.create(tokenType, this);
        token.scan();
        return (T) token;
    }

    public Lexer skipBlank() {
        while (!isEOF()) {
            char ch = source.charAt(position);
            if (!CharacterType.BLANK.isMatch(ch)) {
                break;
            }
            position++;
        }
        return this;
    }

    public boolean isEndOfLine() {
        if (isEOF()) {
            return true;
        }
        return source.charAt(position) == CR && source.charAt(position + 1) == LF;
    }

    public boolean isEOF() {
        return position >= source.length();
    }

    public void throwSyntaxException() throws SyntaxException {
        String fragment = source.substring(Math.max(position - 50, 0), position);
        throw new SyntaxException(position, fragment);
    }
}
