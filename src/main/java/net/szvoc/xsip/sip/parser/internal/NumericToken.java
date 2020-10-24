package net.szvoc.xsip.sip.parser.internal;

public class NumericToken extends Token {
    public NumericToken(Lexer lexer) {
        super(TokenType.NUMERIC, lexer);
    }

    public int getIn32() {
        return Integer.parseInt(super.getTokenValue());
    }

    public long getInt64() {
        return Long.parseLong(super.getTokenValue());
    }

    public float getFloat() {
        return Float.parseFloat(super.getTokenValue());
    }

    @Override
    protected void scan() throws SyntaxException {
        lexer.skipBlank();
        StringBuilder stringBuilder = new StringBuilder();
        boolean dot = false;
        while (!lexer.isEOF()) {
            char ch = lexer.read();
            if (CharacterType.DOT.isMatch(ch)) {
                if (dot) { // 小数点只能出现一次
                    lexer.back();
                    break;
                } else {
                    dot = true;
                    stringBuilder.append(ch);
                }
            } else if (CharacterType.DIGIT.isMatch(ch)) {
                stringBuilder.append(ch);
            } else {
                break;
            }
        }
        if (stringBuilder.length() == 0 || stringBuilder.equals(".")) {
            lexer.throwSyntaxException();
        }
        super.setTokenValue(stringBuilder.toString());
    }
}
