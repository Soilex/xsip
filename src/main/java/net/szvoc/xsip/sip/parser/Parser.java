package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.WordToken;

public abstract class Parser<T extends Header<?>> {
    protected abstract T doParse(Lexer lexer) throws SyntaxException;

    @SuppressWarnings("unchecked")
    public static <E extends Header<?>> E parse(Lexer lexer) throws SyntaxException {
        WordToken nameToken = new WordToken(true, lexer);
        nameToken.match();
        if (lexer.read(CharacterType.COLON) == null) {
            lexer.throwSyntaxException();
        }
        return (E) ParserFactory.create(nameToken.getValue()).doParse(lexer);
    }
}
