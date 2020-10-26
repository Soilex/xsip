package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;

import java.math.BigDecimal;

public class NumericToken extends Token<BigDecimal> {
    public NumericToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected void doScan() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        boolean dot = false;
        while (!lexer.isEOF()) {
            char ch = lexer.peek();
            if (Character.DOT.isMatch(ch)) {
                if (dot) { // 小数点只能出现一次
                    break;
                } else {
                    dot = true;
                    stringBuilder.append(ch);
                    lexer.skip(1);
                }
            } else if (Character.DIGIT.isMatch(ch)) {
                stringBuilder.append(ch);
                lexer.skip(1);
            } else {
                break;
            }
        }
        this.setValue(stringBuilder.length() == 0 || stringBuilder.equals(".") ? null : new BigDecimal(stringBuilder.toString()));
    }
}
