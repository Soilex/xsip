package net.szvoc.xsip.sip.parser.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class ComplexToken<T> extends Token<T> {
    private Holder<T> tokenValueHolder = new Holder<>();
    private List<TokenFuture> futures = new ArrayList<>();

    protected T defaultValue() {
        return null;
    }

    ;

    public ComplexToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    protected <E extends Token> CompletableFuture<E> rule(E subToken) {
        CompletableFuture<E> future = new CompletableFuture<>();
        futures.add(new TokenFuture(subToken, future));
        return future;
    }

    protected abstract void rules(final Holder<T> tokenValueHolder);

    @Override
    public void scan() throws SyntaxException {
        this.tokenValueHolder.setValue(defaultValue());
        this.rules(this.tokenValueHolder);
        super.scan();
        this.setValue(this.tokenValueHolder.getValue(), false);
    }

    @Override
    protected void doScan() throws SyntaxException {
        lexer.markIndex();

        List<Runnable> callbacks = new ArrayList<>();
        for (TokenFuture tf : futures) {
            Token token = tf.getToken();
            CompletableFuture<Token> future = tf.getFuture();
            try {
                token.scan();
                callbacks.add(() -> future.complete(token));
            } catch (SyntaxException ex) {
                if (this.isRequired()) {
                    future.completeExceptionally(ex);
                    throw ex;
                } else {
                    lexer.resetIndex();
                    return;
                }
            }
        }
        callbacks.forEach(Runnable::run);
    }

    @Data
    @AllArgsConstructor
    private static class TokenFuture<E extends Token> {
        private E token;
        private CompletableFuture<E> future;
    }

    @Getter
    @Setter
    static class Holder<T> {
        private T value;
    }
}
