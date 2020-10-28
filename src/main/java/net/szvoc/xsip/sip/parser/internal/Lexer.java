package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.Collection;

public class Lexer {
    private int position = 0;
    private String source;

    public Lexer(String source) {
        this.source = source;
    }

    public Character expect(Collection<CharacterType> expects) {
        if (isEOF()) {
            return null;
        }
        Character ch = source.charAt(position++);
        if (expects != null && !expects.isEmpty() && !CharacterType.isMatch(ch, expects)) {
            position--;
            return null;
        }
        return ch;
    }

    public Character expect(CharacterType... expects) {
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

    public Character unexpect(Collection<CharacterType> unexpects) {
        if (isEOF()) {
            return null;
        }
        Character ch = source.charAt(position++);
        if (unexpects != null && !unexpects.isEmpty() && CharacterType.isMatch(ch, unexpects)) {
            position--;
            return null;
        }
        return ch;
    }

    public Character unexpect(CharacterType... unexpects) {
        if (isEOF()) {
            return null;
        }
        Character ch = source.charAt(position++);
        if (unexpects != null && unexpects.length > 0 && CharacterType.isMatch(ch, unexpects)) {
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
            if (!CharacterType.isMatch(ch, CharacterType.SPACE, CharacterType.TAB)) {
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
        char ch = source.charAt(position);
        return ch == CharacterType.CR.getCode() || ch == CharacterType.LF.getCode();
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
