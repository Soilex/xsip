package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;

public class StringBuffer {
    private int position = 0;
    private String source;

    private static final char CR = '\r';
    private static final char LF = '\n';

    public StringBuffer(String source) {
        this.source = source;
    }

    public Character read(CharacterType... expects) {
        if (isEOF()) {
            return null;
        }
        Character ch = source.charAt(position++);
        if (expects != null && expects.length > 0 && !CharacterType.isMatch(ch, expects)) {
            position--;
            return null;
        }
        return ch;
    }

    public int position() {
        return position;
    }

    public int position(int value) {
        return position = value;
    }

    public StringBuffer skipBlank() {
        while (!isEOF()) {
            char ch = source.charAt(position);
            if (!CharacterType.BLANK.isMatch(ch)) {
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
