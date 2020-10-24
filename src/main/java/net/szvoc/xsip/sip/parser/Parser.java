package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.internal.WordToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.TokenType;

public abstract class Parser<T> {
    public static final char DELIMITER_EQUALS = '=';
    public static final char DELIMITER_COLON = ':';

    private char delimiter; //分隔符

    protected Parser(char delimiter) {
        this.delimiter = delimiter;
    }

    protected abstract T parse(Lexer lexer) throws SyntaxException;

    public static <E> E parser(Lexer lexer, char delimiter) throws SyntaxException {
        WordToken nameToken = lexer.nextToken(TokenType.WORD);
        if (lexer.read() != delimiter) {
            lexer.throwSyntaxException();
        }
        lexer.skipBlank();
        switch (nameToken.getValue()) {
            case HeaderName.ACCEPT:
                return (E) new AcceptParser().parse(lexer);
        }
        return null;
    }
}
