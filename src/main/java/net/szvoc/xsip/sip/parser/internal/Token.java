package net.szvoc.xsip.sip.parser.internal;

import com.google.common.base.Strings;
import lombok.Getter;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public abstract class Token<T> {
    @Getter
    private String id;

    @Getter
    private boolean required;

    @Getter
    private T value;

    protected Lexer lexer;
    private Consumer<T> matchHandler;

    public Token(String id, boolean required, Lexer lexer, Consumer<T> matchHandler) {
        this.id = id;
        this.required = required;
        this.lexer = lexer;
        this.matchHandler = matchHandler;
    }

    public Token(boolean required, Lexer lexer, Consumer<T> matchHandler) {
        this("", required, lexer, matchHandler);
    }

    public Token(String id, boolean required, Lexer lexer) {
        this(id, required, lexer, null);
    }

    public Token(boolean required, Lexer lexer) {
        this("", required, lexer, null);
    }

    public final void match() throws SyntaxException {
        match(true);
    }

    protected void match(boolean handle) throws SyntaxException {
        lexer.markIndex();
        lexer.skipBlank();
        try {
            doMatch();
        } catch (SyntaxException ex){
            lexer.resetIndex();
            throw ex;
        }
        if (handle) {
            invokeHandler();
        }
    }

    protected abstract void doMatch() throws SyntaxException;

    protected final void invokeHandler() {
        if (matchHandler != null) {
            matchHandler.accept(this.value);
        }
    }

    protected final void setValue(T val) throws SyntaxException {
        if (required && isNull(val)) {
            this.lexer.throwSyntaxException();
        }
        this.value = val;
    }

    private boolean isNull(T val) {
        if (val instanceof String) {
            return Strings.isNullOrEmpty((String) val);
        }
        if (val instanceof Iterable) {
            return !((Iterable) val).iterator().hasNext();
        }
        return val == null;
    }
}
