package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
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
    protected boolean doMatch() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            Character ch = lexer.read(CharacterType.ALPHA, CharacterType.DIGIT, CharacterType.MINUS, CharacterType.UNDERSCORE, CharacterType.DOT);
            if (ch != null) {
                stringBuilder.append(ch);
            } else {
                break;
            }
        }
        this.setValue(stringBuilder.toString());
        return stringBuilder.length() > 0;
    }
}
