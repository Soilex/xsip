package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CallIDTests {
    @Test
    void parse() {
        String text = "Call-ID: f81d4fae-7dec-11d0-a765-00a0c91e6bf6@biloxi.com\r\n";
        Header<String> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.CALL_ID);
        assert header.get().equals("f81d4fae-7dec-11d0-a765-00a0c91e6bf6@biloxi.com");
    }
}
