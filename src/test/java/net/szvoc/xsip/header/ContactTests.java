package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Contact;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Iterator;

@SpringBootTest
class ContactTests {
    @Test
    void parse() throws SyntaxException {
        String text = "Contact: \"sonic\" <sip:1001@127.0.0.1:61917;ob;received=192.168.1.2>;tag=123456789;expires=3600, \"Mr. Watson\" <mailto:watson@bell-telephone.com>;q=0.1\r\n";
        Header<Contact> header = Parser.parse(new Lexer(text));
        assert header.getName().equals("Contact");
        Iterator<Contact> iterator = header.iterator();
        Contact first = iterator.next();
        assert first.getName().equals("sonic");
        assert first.getUri().getScheme().equals("sip");
        assert first.getUri().getUser().equals("1001");
        assert first.getUri().getHost().equals("127.0.0.1");
        assert first.getUri().getPort() == 61917;
        assert StringUtils.isBlank(first.getUri().getParameter("ob").getString());
        assert first.getUri().getParameter("received").getString().equals("192.168.1.2");
        assert first.getTag().equals("123456789");
        assert first.getExpires() == 3600;
        assert BigDecimal.valueOf(first.getQ()).equals(BigDecimal.valueOf(1f));
        Contact second = iterator.next();
        assert second.getName().equals("Mr. Watson");
        assert second.getUri().getScheme().equals("mailto");
        assert second.getUri().getUser().equals("watson");
        assert second.getUri().getHost().equals("bell-telephone.com");
        assert second.getUri().getPort() == 0;
        assert BigDecimal.valueOf(second.getQ()).equals(BigDecimal.valueOf(.1f));
    }
}
