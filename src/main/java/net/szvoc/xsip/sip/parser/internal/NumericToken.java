package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class NumericToken extends Token<BigDecimal> {
    private boolean allowDecimal = true;
    private boolean hasBound = false;
    private BigDecimal lowerBound = new BigDecimal(0);
    private BigDecimal upperBound = new BigDecimal(Long.MAX_VALUE);

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

    public NumericToken allowDecimal(boolean allowDecimal) {
        this.allowDecimal = allowDecimal;
        return this;
    }

    public NumericToken range(long lowerBound, long upperBound) {
        this.hasBound = true;
        this.lowerBound = new BigDecimal(lowerBound);
        this.upperBound = new BigDecimal(upperBound);
        return this;
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        while (!lexer.isEOL()) {
            Character ch = allowDecimal ? lexer.expect(CharacterType.DOT, CharacterType.DIGIT) : lexer.expect(CharacterType.DIGIT);
            if (ch == null) {
                break;
            }
            stringBuilder.append(ch);
        }
        BigDecimal value = null;
        try {
            value = new BigDecimal(stringBuilder.toString());
        } catch (NumberFormatException ignore) {
        }
        if (hasBound && (value == null || (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0))) {
            lexer.throwSyntaxException();
        }
        this.setValue(value);

        return value != null;
    }
}
