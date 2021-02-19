package net.szvoc.xsip.sip.common;

import lombok.Getter;

import java.util.Collection;

public enum CharacterType {
    BACKSLASH('\\'),
    QUOTE('\''),
    ALT('@'),
    SPACE(' '),
    TAB('\t'),
    COLON(':'),
    STAR('*'),
    DOLLAR('$'),
    PLUS('+'),
    POUND('#'),
    MINUS('-'),
    DOUBLEQUOTE('\"'),
    TILDE('~'),
    BACK_QUOTE('`'),
    EQUALS('='),
    SEMICOLON(';'),
    SLASH('/'),
    L_SQUARE_BRACKET('['),
    R_SQUARE_BRACKET(']'),
    R_CURLY('}'),
    L_CURLY('{'),
    HAT('^'),
    BAR('|'),
    DOT('.'),
    EXCLAMATION('!'),
    LPAREN('('),
    RPAREN(')'),
    GREATER_THAN('>'),
    LESS_THAN('<'),
    PERCENT('%'),
    QUESTION('?'),
    AND('&'),
    UNDERSCORE('_'),
    COMMA(','),
    CR('\r'),
    LF('\n'),

    DIGIT(1024 + 1),
    ALPHA(1024 + 2);

    @Getter
    private int code;

    CharacterType(int code) {
        this.code = code;
    }

    public boolean isMatch(Character ch) {
        if (ch == null) {
            return false;
        }
        switch (this) {
            case DIGIT:
                return ch >= '0' && ch <= '9';
            case ALPHA:
                return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
            default:
                return this.code == ch;
        }
    }

    public static boolean isMatch(Character ch, Collection<CharacterType> characterTypes) {
        if (ch == null || characterTypes == null || characterTypes.isEmpty()) {
            return false;
        }
        for (CharacterType characterType : characterTypes) {
            if (characterType.isMatch(ch)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMatch(Character ch, CharacterType... characterTypes) {
        if (ch == null || characterTypes == null || characterTypes.length == 0) {
            return false;
        }
        for (CharacterType characterType : characterTypes) {
            if (characterType.isMatch(ch)) {
                return true;
            }
        }
        return false;
    }
}