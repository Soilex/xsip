package net.szvoc.xsip.message;

import lombok.var;
import net.szvoc.xsip.sipbak.core.Request;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegisterTests {

    @Test
    void parse() {
        var text = "REGISTER sip:127.0.0.1:2345 SIP/2.0\r\n" +
                "Via: SIP/2.0/UDP 127.0.0.1:61917;rport;branch=z9hG4bKPjefd6b318b0b64ba2a2edcb8baeb0eb54\r\n" +
                "Max-Forwards: 70\r\n" +
                "From: \"sonic\" <sip:1001@szvoc.net>;tag=21bc75cd4dfb4bc3896fc5c0d0ed9cea\r\n" +
                "To: \"sonic\" <sip:1001@szvoc.net>\r\n" +
                "Call-ID: 4ae1cac6ae3049c1b82771c3842e1672\r\n" +
                "CSeq: 21721 REGISTER\r\n" +
                "User-Agent: MicroSIP/3.19.31\r\n" +
                "Contact: \"sonic\" <sip:1001@127.0.0.1:61917;ob>\r\n" +
                "Expires: 300\r\n" +
                "Allow: PRACK, INVITE, ACK, BYE, CANCEL, UPDATE, INFO, SUBSCRIBE, NOTIFY, REFER, MESSAGE, OPTIONS\r\n" +
                "Content-Length:  0\r\n\r\n";
        var request = Request.parse(null, null, text);
    }

}
