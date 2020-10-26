package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;

public class CharacterToken extends Token<Character> {
    private Character character;

    public CharacterToken(Character character, boolean required, Lexer lexer) {
        super(required, lexer);
        this.character = character;
    }

    @Override
    protected void doScan() throws SyntaxException {
        if (!character.isMatch(lexer.peek())) {
            this.setValue(null);
        } else {
            lexer.skip(1);
            this.setValue(character);
        }
    }
}
