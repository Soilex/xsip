package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

@BindingTokenType(TokenType.NUMERIC)
public class NumericToken extends Token {
    private String value;

    public NumericToken(Lexer lexer) {
        super(TokenType.NUMERIC, lexer);
    }

    public int getIn32() {
        return Integer.parseInt(value);
    }

    public long getInt64() {
        return Long.parseLong(value);
    }

    public float getFloat() {
        return Float.parseFloat(value);
    }

    @Override
    protected void scan() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        boolean dot = false;
        while (!lexer.isEOF()) {
            char ch = lexer.look();
            if (CharacterType.DOT.isMatch(ch)) {
                if (dot) { // 小数点只能出现一次
                    break;
                } else {
                    dot = true;
                    stringBuilder.append(ch);
                    lexer.skip(1);
                }
            } else if (CharacterType.DIGIT.isMatch(ch)) {
                stringBuilder.append(ch);
                lexer.skip(1);
            } else {
                break;
            }
        }
        if (stringBuilder.length() == 0 || stringBuilder.equals(".")) {
            lexer.throwSyntaxException();
        }
        this.value = stringBuilder.toString();
    }
}
