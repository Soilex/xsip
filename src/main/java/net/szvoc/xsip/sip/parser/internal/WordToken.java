package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

@BindingTokenType(TokenType.WORD)
public class WordToken extends Token<String> {
    public WordToken( boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    public void scan() throws SyntaxException {
        super.scan();

        StringBuilder stringBuilder = new StringBuilder();
        while (!lexer.isEOF()) {
            char ch = lexer.read();
            if (Character.isMatch(ch, Character.ALPHA, Character.DIGIT, Character.MINUS, Character.UNDERSCORE, Character.DOT)) {
                stringBuilder.append(ch);
            } else {
                lexer.back();
                break;
            }
        }
        this.setValue(stringBuilder.toString());
    }
}
