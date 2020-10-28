package net.szvoc.xsip.sip.parser.internal;

import com.google.common.base.Strings;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ComplexToken extends Token<Map<String, ?>> {
    private final List<Token<?>> tokens = new ArrayList<>();

    public ComplexToken(String id, boolean required, Lexer lexer, Consumer<Map<String, ?>> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public ComplexToken(boolean required, Lexer lexer, Consumer<Map<String, ?>> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public ComplexToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public ComplexToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    public <T> ComplexToken define(Token<T> token) {
        tokens.add(token);
        return this;
    }

    @Override
    protected boolean match(boolean handle) throws SyntaxException {
        return super.match(handle);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        List<Token<?>> matches = new ArrayList<>();
        for (Token<?> token : tokens) {
            try {
                if (token.match(false)) {
                    matches.add(token);
                }
            } catch (SyntaxException ex) {
                if (this.isRequired()) {
                    throw ex;
                } else {
                    matches.clear();
                    return false;
                }
            }
        }
        if  (isRequired() && matches.isEmpty()) {
            lexer.throwSyntaxException();
        }
        Map<String, ?> tokensValue = this.tokens.stream()
                .filter(p -> !Strings.isNullOrEmpty(p.getId()))
                .collect(Collectors.toMap(p -> p.getId(), p -> p.getValue()));
        this.setValue(tokensValue);
        matches.forEach(c -> c.invokeHandler());
        return matches.size() > 0;
    }
}
