package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.Collection;

public class Lexer {
    private int position = 0;
    private String source;

    private static final char CR = '\r';
    private static final char LF = '\n';

    public Lexer(String source) {
        this.source = source;
    }

    public Character read(Collection<CharacterType> expects) {
        if (isEOF()) {
            return null;
        }
        Character ch = source.charAt(position++);
        if (expects != null && !CharacterType.isMatch(ch, expects)) {
            position--;
            return null;
        }
        return ch;
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
        String fragment = source.substring(Math.max(position - 50, 0), position)
                .replaceAll("\r", "\\r")
                .replaceAll("\n", "\\n");
        throw new SyntaxException(position, fragment);
    }
}
