package net.szvoc.xsip.sip.parser;

public class SyntaxException extends RuntimeException {
    public SyntaxException(int position, String fragment) {
        super("Unexpected token at \"" + fragment + "\", " + position);
    }
}
