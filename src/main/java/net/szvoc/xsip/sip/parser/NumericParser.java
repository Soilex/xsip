package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.NumericToken;

import java.math.BigDecimal;

@BindingHeader({HeaderName.CONTENT_LENGTH, HeaderName.SESSION_EXPIRES, HeaderName.MIN_SE, HeaderName.MAX_FORWARDS, HeaderName.EXPIRES, HeaderName.MIN_EXPIRES, HeaderName.TIMESTAMP})
public class NumericParser extends Parser<BigDecimal> {
    @Override
    protected Header<BigDecimal> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<BigDecimal> header = new Header<>(headerName);
        new NumericToken(true, lexer, header::add).match();
        return header;
    }
}
