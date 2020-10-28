package net.szvoc.xsip.sip.parser.internal;

public interface TokenValueFilter<T> {
    boolean doFilter(Token<T> token, T tokenValue);
}
