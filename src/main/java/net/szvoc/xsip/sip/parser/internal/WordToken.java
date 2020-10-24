package net.szvoc.xsip.sip.parser.internal;

import lombok.Getter;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

@BindingTokenType(TokenType.WORD)
public class WordToken extends Token {
    @Getter
    private String value;

    public WordToken(Lexer lexer) {
        super(TokenType.WORD, lexer);
    }

    @Override
    protected void scan() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        while (!lexer.isEOF()) {
            char ch = lexer.read();
            if (CharacterType.isMatch(ch, CharacterType.ALPHA, CharacterType.DIGIT, CharacterType.MINUS, CharacterType.UNDERSCORE, CharacterType.DOT)) {
                stringBuilder.append(ch);
            } else {
                lexer.back();
                break;
            }
        }
        if (stringBuilder.length() == 0) {
            lexer.throwSyntaxException();
        }
        this.value = stringBuilder.toString();
    }
}
