package net.szvoc.xsip.sip.parser;

public class Lexer {
    private String source;
    private int position = 0;

    public Lexer(String source) {
        this.source = source;
    }

    public char pick() {
        return source.charAt(position++);
    }

    public void back() {
        if (position > 0) {
            position--;
        }
    }

    public Token nextToken(TokenType tokenType) {
        return null;
    }

    public void skipBlank() {
        while (!isEOF()) {
            char ch = source.charAt(position);
            if (!CharacterType.BLANK.isMatch(ch)) {
                break;
            }
            position++;
        }
    }

    public boolean isEOF() {
        return position < source.length();
    }

    void throwSyntaxException() {
        source.substring(Math.max(position - 10, 0), position);
        throw new SyntaxException(position, )
    }
}
