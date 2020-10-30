package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.common.Protocol;
import net.szvoc.xsip.sip.common.Transport;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.header.Via;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ViaTests {
    @Test
    void parse() throws SyntaxException {
        String text = "Via: SIP/2.0/UDP 127.0.0.1:54283;rport;branch=z9hG4bKPj393be92570584d94b167c3470ec70c4b\r\n";
        Header<Via> header = Parser.parse(new Lexer(text));
        Via via = header.get();
        assert header.getName().equals(HeaderName.VIA);
        assert via.getProtocol().equals(Protocol.SIP);
        assert via.getVersion().equals("2.0");
        assert via.getTransport().equals(Transport.UDP);
        assert via.getHost().equals("127.0.0.1");
        assert via.getPort() == 54283;
        assert via.getBranch().equals("z9hG4bKPj393be92570584d94b167c3470ec70c4b");
        assert via.getParameter("rport") != null && !via.getParameter("rport").hasValue();
    }

    @Test
    void parseWithoutPort() throws SyntaxException {
        String text = "Via: SIP/2.0/UDP 127.0.0.1;rport;branch=z9hG4bKPj393be92570584d94b167c3470ec70c4b\r\n";
        Header<Via> header = Parser.parse(new Lexer(text));
        Via via = header.get();
        assert header.getName().equals(HeaderName.VIA);
        assert via.getProtocol().equals(Protocol.SIP);
        assert via.getVersion().equals("2.0");
        assert via.getTransport().equals(Transport.UDP);
        assert via.getHost().equals("127.0.0.1");
        assert via.getPort() == 0;
        assert via.getBranch().equals("z9hG4bKPj393be92570584d94b167c3470ec70c4b");
        assert via.getParameter("rport") != null && !via.getParameter("rport").hasValue();
    }
}
