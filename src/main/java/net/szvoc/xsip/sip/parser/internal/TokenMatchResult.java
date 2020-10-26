package net.szvoc.xsip.sip.parser.internal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.szvoc.xsip.sip.parser.SyntaxException;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenMatchResult<T> {
    private T value;
    private SyntaxException exception;

    public boolean isMatch() {
        return value != null;
    }

    public static <T> TokenMatchResult success(T value) {
        return new TokenMatchResult(value, null);
    }

    public static TokenMatchResult error(SyntaxException exception) {
        return new TokenMatchResult(null, exception);
    }
}
