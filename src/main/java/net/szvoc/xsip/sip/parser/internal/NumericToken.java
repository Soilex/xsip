package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class NumericToken extends Token<BigDecimal> {
    public NumericToken(String id, boolean required, Lexer lexer, Consumer<BigDecimal> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public NumericToken(boolean required, Lexer lexer, Consumer<BigDecimal> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public NumericToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public NumericToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        while (!lexer.isEOL()) {
            Character ch = lexer.read(CharacterType.DOT, CharacterType.DIGIT);
            if (ch == null) {
                break;
            }
            stringBuilder.append(ch);
        }
        BigDecimal decimal = null;
        try {
            decimal = new BigDecimal(stringBuilder.toString());
        } catch (NumberFormatException ignore) {
        }
        this.setValue(decimal);
        return decimal != null;
    }
}
