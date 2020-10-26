package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

import java.math.BigDecimal;

@BindingTokenType(TokenType.NUMERIC)
public class NumericToken extends Token<BigDecimal> {
    public NumericToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    public void scan() throws SyntaxException {
        super.scan();

        StringBuilder stringBuilder = new StringBuilder();
        boolean dot = false;
        while (!lexer.isEOF()) {
            char ch = lexer.look();
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
