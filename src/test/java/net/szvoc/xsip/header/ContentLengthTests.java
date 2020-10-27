package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.common.Protocol;
import net.szvoc.xsip.sip.common.Transport;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.Via;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ContentLengthTests {
    @Test
    void parse() throws SyntaxException {
        String text = "Content-Length:   347";
        Header<BigDecimal> header = Parser.parse(new Lexer(text));
        int contentLength = header.get().intValue();
        assert header.getName().equals("Content-Length");
        assert contentLength == 347;
    }
}
