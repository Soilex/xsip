package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.internal.CharacterToken;
import net.szvoc.xsip.sip.parser.internal.CrlfToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.WordToken;

public abstract class Parser<T> {
    protected abstract Header<T> doParse(String headerName, Lexer lexer) throws SyntaxException;

    @SuppressWarnings("unchecked")
    public static <E> Header<E> parse(Lexer lexer) throws SyntaxException {
        if (new CrlfToken(false, lexer).match()) {
            return (Header<E>) new Header<String>(HeaderName.EMPTY_LINE); // 空行
        } else {
            WordToken nameToken = new WordToken(true, lexer);
            nameToken.match();
            String headerName = nameToken.getValue();
            new CharacterToken(CharacterType.COLON, true, lexer).match();
            Header<E> header = ParserFactory.create(headerName).doParse(headerName, lexer);
            new CrlfToken(true, lexer).match();
            return header;
        }
    }
}
