package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.CallInfo;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

@SpringBootTest
public class CallInfoTests {
    @Test
    void parse() {
        String text = "Call-Info: <http://wwww.example.com/alice/photo.jpg> ;purpose=icon, <http://www.example.com/alice/>;purpose=info\r\n";
        Header<CallInfo> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.CALL_INFO);
        Iterator<CallInfo> iterator = header.iterator();
        CallInfo first = iterator.next();
        assert first.getUri().equals("http://wwww.example.com/alice/photo.jpg");
        assert first.getPurpose().equals("icon");
        CallInfo second = iterator.next();
        assert second.getUri().equals("http://www.example.com/alice/");
        assert second.getPurpose().equals("info");
    }
}
