package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.common.Method;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

@SpringBootTest
class AllowTests {
    @Test
    void parse() {
        String text = "Allow: INVITE, ACK, OPTIONS, CANCEL, BYE\r\n";
        Header<EnumEx<Method>> header = Parser.parse(new Lexer(text));
        Iterator<EnumEx<Method>> iterator = header.iterator();
        assert header.getName().equals("Allow");
        assert iterator.next().equals(Method.INVITE);
        assert iterator.next().equals(Method.ACK);
        assert iterator.next().equals(Method.OPTIONS);
        assert iterator.next().equals(Method.CANCEL);
        assert iterator.next().equals(Method.BYE);
    }
}
