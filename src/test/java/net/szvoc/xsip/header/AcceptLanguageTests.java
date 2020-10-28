package net.szvoc.xsip.header;

import com.google.common.base.Strings;
import net.szvoc.xsip.sip.header.AcceptLanguage;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Iterator;

@SpringBootTest
class AcceptLanguageTests {
    @Test
    void parse() {
        String text = "Accept-Language: da, en-gb;q=0.8, en;q=0.7\r\n";
        Header<AcceptLanguage> header = Parser.parse(new Lexer(text));
        assert header.getName().equals("Accept-Language");

        Iterator<AcceptLanguage> iterator = header.iterator();

        AcceptLanguage first = iterator.next();
        assert first.getLanguage().equals("da");
        assert Strings.isNullOrEmpty(first.getCountry());
        assert BigDecimal.valueOf(first.getQValue()).equals(BigDecimal.valueOf(1f));

        AcceptLanguage second = iterator.next();
        assert second.getLanguage().equals("en");
        assert second.getCountry().equals("gb");
        assert BigDecimal.valueOf(second.getQValue()).equals(BigDecimal.valueOf(0.8f));

        AcceptLanguage third = iterator.next();
        assert third.getLanguage().equals("en");
        assert Strings.isNullOrEmpty(third.getCountry());
        assert BigDecimal.valueOf(third.getQValue()).equals(BigDecimal.valueOf(0.7f));
    }
}
