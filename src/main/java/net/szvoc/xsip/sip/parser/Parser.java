package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.internal.CharacterType;
import net.szvoc.xsip.sip.parser.internal.StringBuffer;
import net.szvoc.xsip.sip.parser.internal.WordToken;

public abstract class Parser<T extends Header> {
    public static final char DELIMITER_COLON = ':';

    protected abstract T doParse(StringBuffer lexer) throws SyntaxException;

    @SuppressWarnings("unckecked")
    public static <E extends Header> E parse(StringBuffer lexer) throws SyntaxException {
        WordToken nameToken = new WordToken(true, lexer);
        if (lexer.read(CharacterType.COLON) == null) {
            lexer.throwSyntaxException();
        }
        return (E) ParserFactory.create(nameToken.getValue()).doParse(lexer);
    }
}
