package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.AuthenticationInfo;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.AuthenticationInfoParser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationInfoTests {
    @Test
    void parse() {
        String text = "Authentication-Info: nextnonce=\"47364c23432d2e131a5fb210812c\";nc=00000002\r\n";
        Header<AuthenticationInfo> header = AuthenticationInfoParser.parse(new Lexer(text));
        assert header.get().getNextNonce().equals("47364c23432d2e131a5fb210812c");
        assert header.get().getNonceCount() == 2;
    }
}
