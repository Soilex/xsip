package net.szvoc.callcenter;

import lombok.var;
import net.szvoc.callcenter.sip.core.HeaderFactory;
import net.szvoc.callcenter.sip.core.Request;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProtocolTests {

    @Test
    void parseRegister() {
        var raw = "REGISTER sip:127.0.0.1:2345 SIP/2.0\r\r\n" +
                "Via: SIP/2.0/UDP 127.0.0.1:61917;rport;branch=z9hG4bKPjefd6b318b0b64ba2a2edcb8baeb0eb54\r\r\n" +
                "Max-Forwards: 70\r\r\n" +
                "From: \"sonic\" <sip:1001@szvoc.net>;tag=21bc75cd4dfb4bc3896fc5c0d0ed9cea\r\r\n" +
                "To: \"sonic\" <sip:1001@szvoc.net>\r\r\n" +
                "Call-ID: 4ae1cac6ae3049c1b82771c3842e1672\r\r\n" +
                "CSeq: 21721 REGISTER\r\r\n" +
                "User-Agent: MicroSIP/3.19.31\r\r\n" +
                "Contact: \"sonic\" <sip:1001@127.0.0.1:61917;ob>\r\r\n" +
                "Expires: 300\r\r\n" +
                "Allow: PRACK, INVITE, ACK, BYE, CANCEL, UPDATE, INFO, SUBSCRIBE, NOTIFY, REFER, MESSAGE, OPTIONS\r\r\n" +
                "Content-Length:  0\r\r\n\r\r\n";
        var request = Request.parse(null, null, raw);
    }

    @Test
    void parseInvite() {
        var raw = "INVITE sip:10086@szvoc.net SIP/2.0\r\n" +
                "Via: SIP/2.0/UDP 127.0.0.1:54283;rport;branch=z9hG4bKPj393be92570584d94b167c3470ec70c4b\r\n" +
                "Max-Forwards: 70\r\n" +
                "From: \"sonic\" <sip:1001@szvoc.net>;tag=f2a60985d61b49218a05b6be26f4e38c\r\n" +
                "To: <sip:10086@szvoc.net>\r\n" +
                "Contact: \"sonic\" <sip:1001@127.0.0.1:54283;ob>\r\n" +
                "Call-ID: 8c597ad5e5fc4caab443bb37af5e3af1\r\n" +
                "CSeq: 17492 INVITE\r\n" +
                "Route: <sip:127.0.0.1;lr>\r\n" +
                "Allow: PRACK, INVITE, ACK, BYE, CANCEL, UPDATE, INFO, SUBSCRIBE, NOTIFY, REFER, MESSAGE, OPTIONS\r\n" +
                "Supported: replaces, 100rel, timer, norefersub\r\n" +
                "Session-Expires: 1800\r\n" +
                "Min-SE: 90\r\n" +
                "User-Agent: MicroSIP/3.19.31\r\n" +
                "Content-Type: application/sdp\r\n" +
                "Content-Length:   347\r\n" +
                "\r\n" +
                "v=0\r\n" +
                "o=- 3810197784 3810197784 IN IP4 172.28.209.20\r\n" +
                "s=pjmedia\r\n" +
                "b=AS:84\r\n" +
                "t=0 0\r\n" +
                "a=X-nat:0\r\n" +
                "m=audio 10000 RTP/AVP 18 9 101\r\n" +
                "c=IN IP4 172.28.209.20\r\n" +
                "b=TIAS:64000\r\n" +
                "a=rtcp:10001 IN IP4 172.28.209.20\r\n" +
                "a=sendrecv\r\n" +
                "a=rtpmap:18 G729/8000\r\n" +
                "a=rtpmap:9 G722/8000\r\n" +
                "a=rtpmap:101 telephone-event/8000\r\n" +
                "a=fmtp:101 0-16\r\n" +
                "a=ssrc:1477579855 cname:66f905605b654260\r\n";
        var request = Request.parse(null, null, raw);
        HeaderFactory.create("Route: <sip:127.0.0.1;lr>");
    }

}
