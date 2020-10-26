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

    public Token(boolean required, Lexer lexer) {
        this.required = required;
        this.lexer = lexer;
    }

    public void scan() throws SyntaxException {
        lexer.markIndex();
        lexer.skipBlank();
        try {
            doScan();
        } catch (SyntaxException ex){
            lexer.resetIndex();
            throw ex;
        }
    }

    protected abstract void doScan() throws SyntaxException;

    protected void setValue(T val) throws SyntaxException {
        setValue(val, this.required);
    }

    protected void setValue(T val, boolean required) throws SyntaxException {
        if (required && isNull(val)) {
            this.lexer.throwSyntaxException();
        }
        this.value = Optional.ofNullable(val);
    }

    private boolean isNull(T val) {
        if (val instanceof String) {
            return Strings.isNullOrEmpty((String) val);
        }
        if (val instanceof Optional) {
            return !((Optional) val).isPresent();
        }
        if (val instanceof Iterable) {
            return !((Iterable) val).iterator().hasNext();
        }
        return val == null;
    }
}
