package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.Stack;

public class Lexer {
    private String source;
    private int position = 0;
    private Stack<Integer> markers = new Stack<>();

    private static final char CR = '\r';
    private static final char LF = '\n';

    public Lexer(String source) {
        this.source = source;
    }

    public java.lang.Character peek() {
        if (isEOF()) {
            return null;
        }
        return source.charAt(position);
    }

    public java.lang.Character read() {
        if (isEOF()) {
            return null;
        }
        return source.charAt(position++);
    }

    public Lexer markIndex() {
        markers.push(position);
        return this;
    }

    public Lexer resetIndex() {
        position = markers.pop();
        return this;
    }

    public Lexer skip(int offset) {
        position += offset;
        return this;
    }

    public Lexer back() {
        position--;
        return this;
    }

    public Lexer skipBlank() {
        while (!isEOF()) {
            char ch = source.charAt(position);
            if (!Character.BLANK.isMatch(ch)) {
                break;
            }
            position++;
        }
        return this;
    }

    public boolean isEOL() {
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
