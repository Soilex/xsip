package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlertInfoTests {
    @Test
    void parse() {
        String text = "Alert-Info: <http://www.example.com/sounds/moo.wav>\r\n";
        Header<String> header = Parser.parse(new Lexer(text));
        assert header.getName().equals("Alert-Info");
        assert header.get().equals("http://www.example.com/sounds/moo.wav");
    }
}
