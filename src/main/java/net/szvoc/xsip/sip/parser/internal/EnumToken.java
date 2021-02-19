package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class EnumToken<T extends Enum<T>> extends Token<EnumEx<T>> {
    private final T[] constants;
    private final T defaultValue;
    private BiFunction<T, String, Boolean> comparer = (a, b) -> a != null && b != null && a.name() != null && a.name().equalsIgnoreCase(b);

    @SuppressWarnings("unchecked")
    public EnumToken(String id, T defaultValue, boolean required, Lexer lexer, Consumer<EnumEx<T>> matchHandler) {
        super(id, required, lexer, matchHandler);
        this.constants = (T[]) defaultValue.getClass().getEnumConstants();
        this.defaultValue = defaultValue;
    }

    public EnumToken(T defaultValue, boolean required, Lexer lexer, Consumer<EnumEx<T>> matchHandler) {
        this("", defaultValue, required, lexer, matchHandler);
    }

    public EnumToken(String id, T defaultValue, boolean required, Lexer lexer) {
        this(id, defaultValue, required, lexer, null);
    }

    public EnumToken(T defaultValue, boolean required, Lexer lexer) {
        this("", defaultValue, required, lexer, null);
    }

    public EnumToken<T> withComparer(BiFunction<T, String, Boolean> comparer) {
        this.comparer = (a, b) -> a != null && b != null && comparer.apply(a, b);
        return this;
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        WordToken token = new WordToken(isRequired(), this.lexer);
        if (token.match()) {
            Optional<T> value = Arrays.stream(this.constants)
                    .filter(c -> this.comparer.apply(c, token.getValue()))
                    .findAny();
            if (isRequired() && !value.isPresent()) {
                this.lexer.throwSyntaxException();
            }
            this.setValue(new EnumEx<>(value.orElse(defaultValue), token.getValue()));
            return true;
        }
        return false;
    }
}
