package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.WordToken;

public abstract class Parser<T> {
    protected abstract Header<T> doParse(String headerName, Lexer lexer) throws SyntaxException;

    @SuppressWarnings("unchecked")
    public static <T> Header<T> parse(Lexer lexer) throws SyntaxException {
        WordToken nameToken = new WordToken(true, lexer);
        nameToken.match();
        if (lexer.read(CharacterType.COLON) == null) {
            lexer.throwSyntaxException();
        }
        return (Header<T>) ParserFactory.create(nameToken.getValue()).doParse(nameToken.getValue(), lexer);
    }
}
