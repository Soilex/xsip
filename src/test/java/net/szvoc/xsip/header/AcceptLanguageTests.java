package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

@SpringBootTest
class AcceptLanguageTests {
    @Test
    void parse() {
        String text = "Accept-Language: es-mx, en, zh-CN\r\n";
        Header<String> header = Parser.parse(new Lexer(text));
        assert header.getName().equals("Accept-Language");

        Iterator<String> iterator = header.iterator();

        String first = iterator.next();
        assert first.equals("es-mx");

        String second = iterator.next();
        assert second.equals("en");

        String third = iterator.next();
        assert third.equals("zh-CN");
    }
}
