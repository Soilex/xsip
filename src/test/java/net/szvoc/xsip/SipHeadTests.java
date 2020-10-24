package net.szvoc.xsip;

import lombok.var;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.SyntaxException;
import net.szvoc.xsip.sipbak.core.HeaderFactory;
import net.szvoc.xsip.sipbak.core.Request;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SipHeadTests {

    @Test
    void parseAccept() throws SyntaxException {
        var raw = "Accept: application/sdp;q-value=0.5;level=1, application/x-private;q-value=0.3, text/html";
        var header = Parser.parser(new Lexer(raw), Parser.DELIMITER_COLON);
    }

}
