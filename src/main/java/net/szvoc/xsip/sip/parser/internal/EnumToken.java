package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.Arrays;
import java.util.function.Consumer;

public class EnumToken extends Token<Enum<?>> {
    private final Enum<?>[] constants;

    public EnumToken(String id, Class<? extends Enum<?>> enumType, boolean required, Lexer lexer, Consumer<Enum<?>> matchHandler) {
        super(id, required, lexer, matchHandler);
        this.constants = enumType.getEnumConstants();
    }

    public EnumToken(Class<? extends Enum<?>> enumType, boolean required, Lexer lexer, Consumer<Enum<?>> matchHandler) {
        super(required, lexer, matchHandler);
        this.constants = enumType.getEnumConstants();
    }

    public EnumToken(String id, Class<? extends Enum<?>> enumType, boolean required, Lexer lexer) {
        super(id, required, lexer);
        this.constants = enumType.getEnumConstants();
    }

    public EnumToken(Class<? extends Enum<?>> enumType, boolean required, Lexer lexer) {
        super(required, lexer);
        this.constants = enumType.getEnumConstants();
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        WordToken token = new WordToken(isRequired(), this.lexer);
        token.match();
        Enum<?> value = Arrays.stream(this.constants)
                .filter(c -> c.name().equalsIgnoreCase(token.getValue()))
                .findAny()
                .orElse(null);
        this.setValue(value);
        return value != null;
    }
}
