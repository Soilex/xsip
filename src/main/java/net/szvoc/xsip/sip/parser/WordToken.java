package net.szvoc.xsip.sip.parser;

public class WordToken extends Token {
    public WordToken(Lexer lexer) {
        super(TokenType.WORD, lexer);
    }

    @Override
    public String getTokenValue() {
        return super.getTokenValue();
    }

    @Override
    public void setTokenValue(String tokenValue) {
        super.setTokenValue(tokenValue);
    }
}
