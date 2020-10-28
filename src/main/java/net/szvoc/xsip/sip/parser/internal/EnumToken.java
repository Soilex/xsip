package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.Arrays;
import java.util.function.Consumer;

public class EnumToken<T extends Enum<T>> extends Token<T> {
    private final T[] constants;
    private final T defaultValue;

    @SuppressWarnings("unchecked")
    public EnumToken(String id, T defaultValue, boolean required, Lexer lexer, Consumer<T> matchHandler) {
        super(id, required, lexer, matchHandler);
        this.constants = (T[]) defaultValue.getClass().getEnumConstants();
        this.defaultValue = defaultValue;
    }

    public EnumToken(T defaultValue, boolean required, Lexer lexer, Consumer<T> matchHandler) {
        this("", defaultValue, required, lexer, matchHandler);
    }

    public EnumToken(String id, T defaultValue, boolean required, Lexer lexer) {
        this(id, defaultValue, required, lexer, null);
    }

    public EnumToken(T defaultValue, boolean required, Lexer lexer) {
        this("", defaultValue, required, lexer, null);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        WordToken token = new WordToken(isRequired(), this.lexer);
        if (token.match()) {
            T value = Arrays.stream(this.constants)
                    .filter(c -> c.name().equalsIgnoreCase(token.getValue()))
                    .findAny()
                    .orElse(defaultValue);
            this.setValue(value);
            return true;
        }
        return false;
    }
}
