package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class CharacterToken extends Token<Character> {
    private Set<CharacterType> expects = new HashSet<>();

    public CharacterToken(String id, boolean required, Lexer lexer, Consumer<Character> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public CharacterToken(boolean required, Lexer lexer, Consumer<Character> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public CharacterToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public CharacterToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    public CharacterToken expect(CharacterType... characterTypes) {
        for (CharacterType characterType : characterTypes) {
            expects.add(characterType);
        }
        return this;
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        Character ch = lexer.expect(expects);
        this.setValue(ch);
        return ch != null;
    }
}
