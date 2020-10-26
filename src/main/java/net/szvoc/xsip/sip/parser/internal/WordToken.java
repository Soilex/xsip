package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public class WordToken extends Token<String> {
    public WordToken(String id, boolean required, Lexer lexer, Consumer<String> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public WordToken(boolean required, Lexer lexer, Consumer<String> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public WordToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public WordToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected void doMatch() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        while (!lexer.isEOF()) {
            char ch = lexer.read();
            if (Character.isMatch(ch, Character.ALPHA, Character.DIGIT, Character.MINUS, Character.UNDERSCORE, Character.DOT)) {
                stringBuilder.append(ch);
            } else {
                lexer.back();
                break;
            }
        }
        this.setValue(stringBuilder.toString());
    }
}
