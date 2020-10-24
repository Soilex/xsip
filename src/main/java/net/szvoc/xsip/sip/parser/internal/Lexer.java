package net.szvoc.xsip.sip.parser.internal;

public class Lexer {
    private String source;
    private int position = 0;

    private static final char CR = '\r';
    private static final char LF = '\n';

    public Lexer(String source) {
        this.source = source;
    }

    public char read() {
        return source.charAt(position++);
    }

    public void skip(int offset) {
        position += offset;
    }

    public <T extends Token> T nextToken(TokenType tokenType) throws SyntaxException {
        Token token = null;
        switch (tokenType) {
            case WORD:
                token = new WordToken(this);
                break;
            case PARAMETER:
                token = new ParameterToken(this);
                break;
            default:
                throwSyntaxException();
        }
        token.scan();
        return (T) token;
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

    public boolean isEndOfLine() {
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
