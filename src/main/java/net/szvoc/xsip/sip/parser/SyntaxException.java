package net.szvoc.xsip.sip.parser;

public class SyntaxException extends Exception {
    public SyntaxException(int position, String source) {
        super("Unexpected token at " + source + ", " + position);
    }
}
