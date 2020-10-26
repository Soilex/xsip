package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.TokenType;
import net.szvoc.xsip.sip.parser.internal.WordToken;

public abstract class Parser<T> {
    public static final char DELIMITER_EQUALS = '=';
    public static final char DELIMITER_COLON = ':';

    private char delimiter; //分隔符

    protected Parser(char delimiter) {
        this.delimiter = delimiter;
    }

    protected abstract T parse(Lexer lexer) throws SyntaxException;

    public static <E extends Header> E parser(Lexer lexer, char delimiter) throws SyntaxException {
        WordToken nameToken = new WordToken(true, lexer);
        if (lexer.read() != delimiter) {
            lexer.throwSyntaxException();
        }
        return (E) ParserFactory.create(nameToken.getValue().get()).parse(lexer);
    }
}
