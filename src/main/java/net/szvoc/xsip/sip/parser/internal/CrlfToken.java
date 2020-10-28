package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public class CrlfToken extends Token<String> {
    public CrlfToken(String id, boolean required, Lexer lexer, Consumer<String> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public CrlfToken(boolean required, Lexer lexer, Consumer<String> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public CrlfToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public CrlfToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        Character cr = lexer.expect(CharacterType.CR);
        Character lf = lexer.expect(CharacterType.LF);
        if (lf == null && isRequired()) {
            lexer.throwSyntaxException();
        }
        this.setValue("" + cr + lf);
        return lf != null;
    }
}
