package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.ContactHeader;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.StringBuffer;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContactTests {

    @Test
    void parse() throws SyntaxException {
        String text = "Contact: \"sonic\" <sip:1001@127.0.0.1:61917;ob;received=192.168.1.2>";
        ContactHeader header = Parser.parse(new StringBuffer(text));
        assert header.getName().equals("Contact");

        ContactHeader.Contact contact = header.get();

        assert contact.getName().equals("sonic");
        assert contact.getUri().getSchema().equals("sip");
        assert contact.getUri().getUser().equals("1001");
        assert contact.getUri().getHost().equals("127.0.0.1");
        assert contact.getUri().getPort() == 61917;
        assert StringUtils.isBlank(contact.getUri().getParameter("ob").getString());
        assert contact.getUri().getParameter("received").getString().equals("192.168.1.2");
    }

}
