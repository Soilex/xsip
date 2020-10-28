package net.szvoc.xsip.sip.parser.internal;

import com.google.common.collect.Sets;
import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.HashSet;
import java.util.function.Consumer;

public class WordToken extends Token<String> {
    private HashSet<CharacterType> unexpects = Sets.newHashSet(
            CharacterType.SPACE,
            CharacterType.TAB,
            CharacterType.CR,
            CharacterType.LF,
            CharacterType.COLON,
            CharacterType.SEMICOLON,
            CharacterType.COMMA,
            CharacterType.EQUALS,
            CharacterType.LESS_THAN,
            CharacterType.GREATER_THAN,
            CharacterType.SLASH);

    public WordToken(String id, boolean required, Lexer lexer, Consumer<String> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public WordToken(boolean required, Lexer lexer, Consumer<String> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public WordToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public WordToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    public WordToken unexpect(CharacterType... characterTypes) {
        for (CharacterType characterType : characterTypes) {
            unexpects.add(characterType);
        }
        return this;
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        while (!lexer.isEOL()) {
            Character ch = lexer.unexpect(unexpects);
            if (ch != null) {
                stringBuilder.append(ch);
            } else {
                break;
            }
        }
        this.setValue(stringBuilder.toString());
        return stringBuilder.length() > 0;
    }
}
