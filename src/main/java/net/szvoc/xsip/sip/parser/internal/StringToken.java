package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public class StringToken extends Token<String> {
    private CharacterType startWith;
    private CharacterType endWith;

    public StringToken(String id, boolean required, Lexer lexer, Consumer<String> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public StringToken(boolean required, Lexer lexer, Consumer<String> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public StringToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public StringToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    public StringToken startWith(CharacterType characterType) {
        this.startWith = characterType;
        return this;
    }

    public StringToken endWith(CharacterType characterType) {
        this.endWith = characterType;
        return this;
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        if (startWith != null && lexer.expect(startWith) == null) {
            if (isRequired()) {
                lexer.throwSyntaxException();
            } else {
                this.setValue(null);
                return false;
            }
        }
        while (!lexer.isEOL()) {
            Character ch = lexer.expect();
            if (endWith != null && endWith.isMatch(ch)) {
                break;
            }
            if (startWith != null && startWith.isMatch(ch)) {
                lexer.throwSyntaxException();
            }
            stringBuilder.append(ch);
        }
        this.setValue(stringBuilder.toString());
        return stringBuilder.length() > 0;
    }
}
