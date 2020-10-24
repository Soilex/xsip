package net.szvoc.xsip.token;

import net.szvoc.xsip.sip.common.URI;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.TokenType;
import net.szvoc.xsip.sip.parser.internal.UriToken;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UriTests {

    @Test
    void parse() throws SyntaxException {
        UriToken token = new Lexer("sip:1001@127.0.0.1:61917").nextToken(TokenType.URI);
        URI uri = token.getValue();
        assert uri.getSchema().equals("sip");
        assert uri.getUser().equals("1001");
        assert uri.getHost().equals("127.0.0.1");
        assert uri.getPort() == 61917;
    }

    @Test
    void parseWithParameters() throws SyntaxException {
        UriToken token = new Lexer("sip:1001@127.0.0.1:61917;ob;received=192.168.1.1\r\n").nextToken(TokenType.URI);
        URI uri = token.getValue();
        assert uri.getSchema().equals("sip");
        assert uri.getUser().equals("1001");
        assert uri.getHost().equals("127.0.0.1");
        assert uri.getPort() == 61917;
        assert StringUtils.isBlank(uri.getParameter("ob").getString());
        assert uri.getParameter("received").getString().equals("192.168.1.1");
    }

    @Test
    void parseNoUser() throws SyntaxException {
        UriToken token = new Lexer("sip:127.0.0.1:61917").nextToken(TokenType.URI);
        URI uri = token.getValue();
        assert uri.getSchema().equals("sip");
        assert StringUtils.isBlank(uri.getUser());
        assert uri.getHost().equals("127.0.0.1");
        assert uri.getPort() == 61917;
    }

    @Test
    void parseNoUserAndPort() throws SyntaxException {
        UriToken token = new Lexer("sip:cc.szvoc.net").nextToken(TokenType.URI);
        URI uri = token.getValue();
        assert uri.getSchema().equals("sip");
        assert StringUtils.isBlank(uri.getUser());
        assert uri.getHost().equals("cc.szvoc.net");
        assert uri.getPort() == 0;
    }
}
