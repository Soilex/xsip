package net.szvoc.xsip.header;

import net.szvoc.xsip.sip.header.ContentDisposition;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.Parser;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContentDispositionTests {
    @Test
    void parse() {
        String text = "Content-Disposition: attachment; filename=\"filename.jpg\"\r\n";
        Header<ContentDisposition> header = Parser.parse(new Lexer(text));
        assert header.getName().equals(HeaderName.CONTENT_DISPOSITION);
        assert header.get().getDispositionType().equals("attachment");
        assert header.get().getParameterValue("filename", "").equals("filename.jpg");
    }
}
