package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Contact;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToTests {
    @Test
    void parse() throws SyntaxException {
        String text = "To: \"sonic\" <sip:1001@szvoc.net>\r\n";
        Header<Contact> header = Parser.parse(new Lexer(text));
        Contact contact = header.get();
        assert header.getName().equals("To");
        assert contact.getName().equals("sonic");
        assert contact.getUri().getScheme().equals("sip");
        assert contact.getUri().getUser().equals("1001");
        assert contact.getUri().getHost().equals("szvoc.net");
        assert contact.getUri().getPort() == 0;
    }

    @Test
    void parseWithoutName() throws SyntaxException {
        String text = "To: <sip:1001@szvoc.net>\r\n";
        Header<Contact> header = Parser.parse(new Lexer(text));
        Contact contact = header.get();
        assert header.getName().equals("To");
        assert contact.getName() == null;
        assert contact.getUri().getScheme().equals("sip");
        assert contact.getUri().getUser().equals("1001");
        assert contact.getUri().getHost().equals("szvoc.net");
        assert contact.getUri().getPort() == 0;
    }
}
