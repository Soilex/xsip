package net.szvoc.xsip.header;

import com.google.common.base.Strings;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
public class ContentLanguageTests {
    @Test
    void parse() {
        String text = "Content-Language: zh-CN\r\n";
        Header<Locale> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.CONTENT_LANGUAGE);
        assert header.get().getLanguage().equals("zh");
        assert header.get().getCountry().equals("CN");
    }

    @Test
    void parseWithoutCountry() {
        String text = "Content-Language: en\r\n";
        Header<Locale> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.CONTENT_LANGUAGE);
        assert header.get().getLanguage().equals("en");
        assert Strings.isNullOrEmpty(header.get().getCountry());
    }
}
