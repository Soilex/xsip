package net.szvoc.xsip.sip.common;

import lombok.Getter;

import java.util.Collection;

public enum CharacterType {
    BACKSLASH((int) '\\'),
    QUOTE((int) '\''),
    ALT((int) '@'),
    SPACE((int) ' '),
    TAB((int) '\t'),
    COLON((int) ':'),
    STAR((int) '*'),
    DOLLAR((int) '$'),
    PLUS((int) '+'),
    POUND((int) '#'),
    MINUS((int) '-'),
    DOUBLEQUOTE((int) '\"'),
    TILDE((int) '~'),
    BACK_QUOTE((int) '`'),
    EQUALS((int) '='),
    SEMICOLON((int) ';'),
    SLASH((int) '/'),
    L_SQUARE_BRACKET((int) '['),
    R_SQUARE_BRACKET((int) ']'),
    R_CURLY((int) '}'),
    L_CURLY((int) '{'),
    HAT((int) '^'),
    BAR((int) '|'),
    DOT((int) '.'),
    EXCLAMATION((int) '!'),
    LPAREN((int) '('),
    RPAREN((int) ')'),
    GREATER_THAN((int) '>'),
    LESS_THAN((int) '<'),
    PERCENT((int) '%'),
    QUESTION((int) '?'),
    AND((int) '&'),
    UNDERSCORE((int) '_'),
    COMMA((int)','),
    CR((int)'\r'),
    LF((int)'\n'),

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