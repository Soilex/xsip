package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.AlertInfo;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlertInfoTests {
    @Test
    void parse() {
        String text = "Alert-Info: <http://www.example.com/sounds/moo.wav> no sip head found.\r\n";
        Header<AlertInfo> header = Parser.parse(new Lexer(text));
        AlertInfo alertInfo = header.get();
        assert header.getName().equals("Alert-Info");
        assert alertInfo.getUri().equals("http://www.example.com/sounds/moo.wav");
        assert alertInfo.getMessage().equals("no sip head found.");
    }
}
