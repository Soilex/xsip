package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.AcceptHeader;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Iterator;

@SpringBootTest
class AcceptTests {

    @Test
    void parse() throws SyntaxException {
        String text = "Accept: application/sdp;q-value=0.5;level=1, application/x-private;q-value=0.3, text/html\r\n";
        AcceptHeader header = Parser.parser(new Lexer(text), Parser.DELIMITER_COLON);
        assert header.getName().equals("Accept");

        Iterator<AcceptHeader.ContentType> iterator = header.iterator();

        AcceptHeader.ContentType first = iterator.next();
        assert first.getContentType().equals("application");
        assert first.getContentSubType().equals("sdp");
        assert BigDecimal.valueOf(first.getQValue()).compareTo(BigDecimal.valueOf(0.5f)) == 0;
        assert first.getParameter("level").getInt32() == 1;

        AcceptHeader.ContentType second = iterator.next();
        assert second.getContentType().equals("application");
        assert second.getContentSubType().equals("x-private");
        assert BigDecimal.valueOf(second.getQValue()).compareTo(BigDecimal.valueOf(0.3f)) == 0;

        AcceptHeader.ContentType third = iterator.next();
        assert third.getContentType().equals("text");
        assert third.getContentSubType().equals("html");
        assert BigDecimal.valueOf(third.getQValue()).compareTo(BigDecimal.valueOf(1)) == 0;
    }

}
