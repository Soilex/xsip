package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Contact;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FromTests {
    @Test
    void parse() throws SyntaxException {
        String text = "From: \"sonic\" <sip:1001@szvoc.net>;tag=21bc75cd4dfb4bc3896fc5c0d0ed9cea";
        Header<Contact> header = Parser.parse(new Lexer(text));
        Contact contact = header.get();
        assert header.getName().equals("From");
        assert contact.getName().equals("sonic");
        assert contact.getUri().getSchema().equals("sip");
        assert contact.getUri().getUser().equals("1001");
        assert contact.getUri().getHost().equals("szvoc.net");
        assert contact.getUri().getPort() == 0;
        assert contact.getTag().equals("21bc75cd4dfb4bc3896fc5c0d0ed9cea");
    }
}
