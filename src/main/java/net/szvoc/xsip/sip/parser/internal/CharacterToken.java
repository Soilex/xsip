package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public class CharacterToken extends Token<Character> {
    private Character character;

    public CharacterToken(Character character, boolean required, Lexer lexer, Consumer<Character> matchHandler) {
        super(required, lexer, matchHandler);
        this.character = character;
    }

    public CharacterToken(Character character, boolean required, Lexer lexer) {
        this(character, required, lexer, null);
    }

    @Override
    protected void doMatch() throws SyntaxException {
        if (!character.isMatch(lexer.peek())) {
            this.setValue(null);
        } else {
            lexer.skip(1);
            this.setValue(character);
        }
    }
}
