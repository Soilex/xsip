package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.AcceptEncoding;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.*;

@BindingHeader(HeaderName.ACCEPT_ENCODING)
public class AcceptEncodingParser extends Parser<AcceptEncoding> {
    @Override
    protected Header<AcceptEncoding> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<AcceptEncoding> header = new Header<>(headerName);
        resolve(header, lexer, () -> {
            AcceptEncoding acceptEncoding = new AcceptEncoding();
            new ComplexToken(true, lexer, t -> header.add(acceptEncoding))
                    .define(new WordToken(true, lexer, acceptEncoding::setEncoding)
                            .allow(CharacterType.STAR)
                            .filter((token, tokenValue) -> tokenValue.equals("*") || !tokenValue.startsWith("*")))
                    .define(new ParametersToken(false, lexer, acceptEncoding::setParameters))
                    .match();
        });
        return header;
    }
}
