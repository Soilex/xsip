package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContentEncodingTests {
    @Test
    void parse() {
        String text = "Content-Encoding: gzip\r\n";
        Header<String> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.CONTENT_ENCODING);
        assert header.get().equals("gzip");
    }
}
