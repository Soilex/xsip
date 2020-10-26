package net.szvoc.xsip.sip.parser.internal;

import com.google.common.base.Strings;
import lombok.Getter;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.Optional;

public abstract class Token<T> {
    @Getter
    private boolean required;

    @Getter
    private Optional<T> value = Optional.empty();

    protected Lexer lexer;

    public boolean hasValue() {
        return this.value.isPresent();
    }

    public Token(boolean required, Lexer lexer) {
        this.required = required;
        this.lexer = lexer;
    }

    public void scan() throws SyntaxException {
        lexer.skipBlank();
    }

    protected void setValue(T val) throws SyntaxException {
        if (isNull(val) && this.required) {
            this.lexer.throwSyntaxException();
        }
        this.value = Optional.ofNullable(val);
    }

    private boolean isNull(T val) {
        if (val instanceof String) {
            return Strings.isNullOrEmpty((String) val);
        }
        return val == null;
    }
}
