package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.common.Method;
import net.szvoc.xsip.sip.header.CSeq;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CseqTests {
    @Test
    void parse() {
        String text = "CSeq: 4711 INVITE\r\n";
        Header<CSeq> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.CSEQ);
        assert header.get().getSn() == 4711;
        assert header.get().getMethod().equals(Method.INVITE);
    }
}
