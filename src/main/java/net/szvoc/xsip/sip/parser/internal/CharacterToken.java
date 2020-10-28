package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public class CharacterToken extends Token<Character> {
    private CharacterType characterType;

    public CharacterToken(String id, CharacterType character, boolean required, Lexer lexer, Consumer<Character> matchHandler) {
        super(id, required, lexer, matchHandler);
        this.characterType = character;
    }

    public CharacterToken(CharacterType character, boolean required, Lexer lexer, Consumer<Character> matchHandler) {
        super(required, lexer, matchHandler);
        this.characterType = character;
    }

    public CharacterToken(String id, CharacterType character, boolean required, Lexer lexer) {
        super(id, required, lexer);
        this.characterType = character;
    }

    public CharacterToken(CharacterType character, boolean required, Lexer lexer) {
        super(required, lexer);
        this.characterType = character;
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        Character ch = lexer.expect(this.characterType);
        this.setValue(ch);
        return ch != null;
    }
}
