package net.szvoc.xsip.sip.parser.internal;

import org.springframework.util.StringUtils;

public class WordToken extends Token {
    public WordToken(Lexer lexer) {
        super(TokenType.WORD, lexer);
    }

    @Override
    public String getTokenValue() {
        return super.getTokenValue();
    }

    @Override
    protected void scan() throws SyntaxException {
        lexer.skipBlank();
        StringBuilder stringBuilder = new StringBuilder();
        while (!lexer.isEOF()) {
            char ch = lexer.read();
            if (CharacterType.isMatch(ch, CharacterType.ALPHA, CharacterType.DIGIT, CharacterType.MINUS, CharacterType.UNDERSCORE, CharacterType.DOT)) {
                stringBuilder.append(ch);
            } else {
                lexer.skip(-1);
                break;
            }
        }
        String value = stringBuilder.toString();
        if (StringUtils.isEmpty(value)) {
            lexer.throwSyntaxException();
        }
        super.setTokenValue(value);
    }
}
