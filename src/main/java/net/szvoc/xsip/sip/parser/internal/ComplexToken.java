package net.szvoc.xsip.sip.parser.internal;

import javafx.util.Pair;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@BindingTokenType(TokenType.COMPLEX)
public abstract class ComplexToken<T> extends Token<T> {
    private List<Pair<Token, CompletableFuture<TokenMatchResult>>> definitions = new ArrayList<>();

    protected <E> CompletableFuture<TokenMatchResult<E>> definition(Token<E> token) {
        CompletableFuture<TokenMatchResult<E>> future = new CompletableFuture<>();
        definitions.add(new Pair(token, future));
        return future;
    }

    public ComplexToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    public void scan() throws SyntaxException {
        super.scan();

        lexer.markIndex();
        for (Pair<Token, CompletableFuture<TokenMatchResult>> definition : definitions) {
            Token token = definition.getKey();
            CompletableFuture<TokenMatchResult> future = definition.getValue();
            try {
                token.scan();
                future.complete(TokenMatchResult.success(token.getValue()));
            } catch (SyntaxException ex) {
                lexer.resetIndex();
                future.completeExceptionally(ex);
                throw ex;
            }
        }
    }
}
