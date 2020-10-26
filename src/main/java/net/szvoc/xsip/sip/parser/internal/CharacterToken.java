package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

@BindingTokenType(TokenType.NUMERIC)
public class CharacterToken extends Token<Character> {
    private Character character;

    public CharacterToken(Character character, boolean required, Lexer lexer) {
        super(required, lexer);
        this.character = character;
    }

    @Override
    public void scan() throws SyntaxException {
        super.scan();

        if (character.isMatch(lexer.look())) {
           lexer.skip(1);
        } else if (isRequired()) {
            lexer.throwSyntaxException();
        }
    }
}
