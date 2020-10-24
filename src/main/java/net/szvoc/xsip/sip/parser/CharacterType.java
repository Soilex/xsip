package net.szvoc.xsip.sip.parser;

import lombok.Getter;

public enum CharacterType {
    BACKSLASH((int)'\\'),
    QUOTE((int)'\''),
    AT((int)'@'),
    SP((int)' '),
    TAB((int)'\t'),
    COLON((int)':'),
    STAR((int)'*'),
    DOLLAR((int)'$'),
    PLUS((int)'+'),
    POUND((int)'#'),
    MINUS((int)'-'),
    DOUBLEQUOTE((int)'\"'),
    TILDE((int)'~'),
    BACK_QUOTE((int)'`'),
    NULL((int)'\0'),
    EQUALS((int)'='),
    SEMICOLON((int)';'),
    SLASH((int)'/'),
    L_SQUARE_BRACKET((int)'['),
    R_SQUARE_BRACKET((int)']'),
    R_CURLY((int)'}'),
    L_CURLY((int)'{'),
    HAT((int)'^'),
    BAR((int)'|'),
    DOT((int)'.'),
    EXCLAMATION((int)'!'),
    LPAREN((int)')'),
    RPAREN((int)')'),
    GREATER_THAN((int)'>'),
    LESS_THAN((int)'<'),
    PERCENT((int)'%'),
    QUESTION((int)'?'),
    AND((int)'&'),
    UNDERSCORE((int)'_'),

    DIGIT(1024 + 1),
    ALPHA(1024 + 2),
    BLANK(1024 + 3);

    @Getter
    private int code;

    CharacterType(int code) {
        this.code = code;
    }

    public boolean isMatch(char ch) {
        switch (this) {
            case DIGIT:
                return ch >= '0' && ch <= '9';
            case ALPHA:
                return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
            case BLANK:
                return ch == SP.code || ch == TAB.code;
            default:
                return this.code == ch;
        }
    }
}