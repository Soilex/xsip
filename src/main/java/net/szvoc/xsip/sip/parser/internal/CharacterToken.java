package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public class CharacterToken extends Token<Character> {
    private CharacterType characterType;

    public CharacterToken(CharacterType character, boolean required, Lexer lexer, Consumer<Character> matchHandler) {
        super(required, lexer, matchHandler);
        this.characterType = character;
    }

    public CharacterToken(CharacterType character, boolean required, Lexer lexer) {
        this(character, required, lexer, null);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        Character ch = lexer.read(this.characterType);
        this.setValue(ch);
        return ch != null;
    }
}
