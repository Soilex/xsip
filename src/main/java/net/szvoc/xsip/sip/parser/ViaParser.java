package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.common.Protocol;
import net.szvoc.xsip.sip.common.Transport;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.header.Via;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.*;

@BindingHeader(HeaderName.VIA)
public class ViaParser extends Parser<Via> {
    @Override
    protected Header<Via> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException {
        Header<Via> header = new Header<>(headerName);
        Via via = new Via();
        new EnumToken<Protocol>(Protocol.UNKNOWN, true, lexer, via::setProtocol).match();
        new CharacterToken(true, lexer).expect(CharacterType.SLASH).match();
        new WordToken(true, lexer, via::setVersion).match();
        new CharacterToken(true, lexer).expect(CharacterType.SLASH).match();
        new EnumToken<Transport>(Transport.UNKNOWN, true, lexer, via::setTransport).match();
        new WordToken(true, lexer, via::setHost).match();
        new ComplexToken(false, lexer)
                .define(new CharacterToken(true, lexer).expect(CharacterType.COLON))
                .define(new NumericToken(true, lexer, c -> via.setPort(c.intValue()))).match();
        new ParametersToken(false, lexer, via::setParameters).match();
        header.add(via);
        return header;
    }
}
