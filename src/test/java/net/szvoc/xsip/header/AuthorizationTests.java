package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Authorization;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthorizationTests {
    @Test
    void parse() {
        String text = "Authorization: Digest username=\"Alice\", realm=\"atlanta.com\", nonce=\"84a4cc6f3082121f32b42a2187831a9e\", response=\"7587245234b3434cc3412213e5f113a5432\"\r\n";
        Header<Authorization> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.AUTHORIZATION);
        assert header.get().getScheme().equals("Digest");
        assert header.get().getUserName().equals("Alice");
        assert header.get().getRealm().equals("atlanta.com");
        assert header.get().getNonce().equals("84a4cc6f3082121f32b42a2187831a9e");
        assert header.get().getResponse().equals("7587245234b3434cc3412213e5f113a5432");
    }
}
