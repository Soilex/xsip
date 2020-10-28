package net.szvoc.xsip.sip.parser.internal;

import com.google.common.base.Strings;
import lombok.Getter;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.ArrayList;
import java.util.List;
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
    private int mark;

    private List<TokenValueFilter<T>> tokenValueFilterChain = new ArrayList<>();

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

    public final boolean match() throws SyntaxException {
        return match(true);
    }

    public Token<T> filter(TokenValueFilter<T> filter) {
        tokenValueFilterChain.add(filter);
        return this;
    }

    protected void markIndex() {
        this.mark = lexer.position();
    }

    protected void resetIndex() {
        lexer.position(this.mark);
    }

    protected boolean match(boolean handle) throws SyntaxException {
        markIndex();
        lexer.skipBlank();
        try {
            if (!doMatch()) {
                resetIndex();
                return false;
            } else {
                for (TokenValueFilter<T> filter : tokenValueFilterChain) {
                    if (!filter.doFilter(this, this.getValue())) {
                        resetIndex();
                        return false;
                    }
                }
            }
        } catch (SyntaxException ex) {
            resetIndex();
            throw ex;
        }
        if (handle) {
            invokeHandler();
        }
        return true;
    }

    protected abstract boolean doMatch() throws SyntaxException;

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
