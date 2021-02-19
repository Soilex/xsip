package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Month;
import java.time.ZonedDateTime;

@SpringBootTest
public class DateTests {
    @Test
    void parse() throws SyntaxException {
        String text = "Date: Tue, 15 Nov 1994 08:12:31 GMT\r\n";
        Header<ZonedDateTime> header = Parser.parse(new Lexer(text));
        ZonedDateTime date = header.get();
        assert header.getName().equals(HeaderName.DATE);
        assert date.getYear() == 1994;
        assert date.getMonth() == Month.NOVEMBER;
        assert date.getDayOfMonth() == 15;
        assert date.getHour() == 8;
        assert date.getMinute() == 12;
        assert date.getSecond() == 31;
    }
}
