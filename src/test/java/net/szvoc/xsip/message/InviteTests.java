package net.szvoc.xsip.message;

import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InviteTests {

    @Test
    void parse() {
        var text = "INVITE sip:10086@szvoc.net SIP/2.0\r\n" +
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
//        var request = Request.parse(null, null, text);
    }

}
