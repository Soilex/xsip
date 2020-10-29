package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.ContentType;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Iterator;

@SpringBootTest
class ContentTypeTests {
    @Test
    void parse() throws SyntaxException {
        String text = "Content-Type: application/sdp;charset=utf8\r\n";
        Header<ContentType> header = Parser.parse(new Lexer(text));
        assert header.getName().equals("Content-Type");
        assert header.get().getMainType().equals("application");
        assert header.get().getSubType().equals("sdp");
        assert header.get().getParameterValue("charset", "").equals("utf8");
    }
}
