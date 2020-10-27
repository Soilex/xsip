package net.szvoc.xsip.sip.parser.internal;

import com.google.common.base.Strings;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class ComplexToken extends Token<Map<String, ?>> {
    private final List<Token<?>> tokens = new ArrayList<>();

    public ComplexToken(String id, boolean required, StringBuffer lexer, Consumer<Map<String, ?>> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public ComplexToken(boolean required, StringBuffer lexer, Consumer<Map<String, ?>> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public ComplexToken(String id, boolean required, StringBuffer lexer) {
        super(id, required, lexer);
    }

    public ComplexToken(boolean required, StringBuffer lexer) {
        super(required, lexer);
    }

    protected <T> void rule(Token<T> token) {
        tokens.add(token);
    }

    protected abstract void rules();

    @Override
    protected void match(boolean handle) throws SyntaxException {
        this.rules();
        super.match(handle);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        List<Token<?>> matches = new ArrayList<>();
        for (Token<?> token : tokens) {
            try {
                token.match(false);
                matches.add(token);
            } catch (SyntaxException ex) {
                if (this.isRequired()) {
                    throw ex;
                } else {
                    matches.clear();
                    return false;
                }
            }
        }
        Map<String, ?> tokensValue = this.tokens.stream()
                .filter(p -> !Strings.isNullOrEmpty(p.getId()))
                .collect(Collectors.toMap(p -> p.getId(), p -> p.getValue()));
        this.setValue(tokensValue);
        matches.forEach(c -> c.invokeHandler());
        return matches.size() > 0;
    }
}
