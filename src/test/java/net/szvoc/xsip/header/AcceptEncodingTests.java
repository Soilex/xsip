package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.AcceptEncoding;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Iterator;

@SpringBootTest
class AcceptEncodingTests {
    @Test
    void parse() throws SyntaxException {
        String text = "Accept-Encoding: deflate, gzip;q=1.0, *;q=0.5\r\n";
        Header<AcceptEncoding> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.ACCEPT_ENCODING);

        Iterator<AcceptEncoding> iterator = header.iterator();

        AcceptEncoding first = iterator.next();
        assert first.getEncoding().equals("deflate");
        assert BigDecimal.valueOf(first.getQ()).compareTo(BigDecimal.valueOf(1f)) == 0;

        AcceptEncoding second = iterator.next();
        assert second.getEncoding().equals("gzip");
        assert BigDecimal.valueOf(second.getQ()).compareTo(BigDecimal.valueOf(1f)) == 0;

        AcceptEncoding third = iterator.next();
        assert third.getEncoding().equals("*");
        assert BigDecimal.valueOf(third.getQ()).compareTo(BigDecimal.valueOf(0.5f)) == 0;
    }
}
