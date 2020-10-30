package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.internal.*;

import java.util.Optional;

public abstract class Parser<T> {
    protected abstract Header<T> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException;

    /**
     * 辅助方法
     * 从多值SIP头域分离出单个值
     */
    protected void resolve(Header<T> header, Lexer lexer, Runnable handler) {
        while (!lexer.isEOL()) {
            if (header.containsValue() && lexer.expect(CharacterType.COMMA) == null) {
                break;
            }
            handler.run();
        }
    }

    @SuppressWarnings("unchecked")
    public static <E> Header<E> parse(Lexer lexer) throws SyntaxException {
        if (new CrlfToken(false, lexer).match()) {
            return (Header<E>) new Header<String>(new EnumEx<>(HeaderName.EMPTY_LINE, null)); // 空行
        } else {
            WordToken nameToken = new WordToken(true, lexer);
            nameToken.match();
            EnumEx<HeaderName> headerName = new EnumEx<>(Optional.ofNullable(HeaderName.textOf(nameToken.getValue())).orElse(HeaderName.UNKNOWN), nameToken.getValue());
            new CharacterToken(true, lexer).expect(CharacterType.COLON).match();
            Header<E> header = ParserFactory.create(headerName.getValue()).doParse(headerName, lexer);
            new CrlfToken(true, lexer).match();
            return header;
        }
    }
}
