package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
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
    protected Header<Via> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<Via> header = new Header<>(headerName);
        Via via = new Via();
        new EnumToken(Protocol.class, true, lexer, c -> via.setProtocol((Protocol) c)).match();
        new CharacterToken(CharacterType.SLASH, true, lexer).match();
        new WordToken(true, lexer, via::setVersion).match();
        new CharacterToken(CharacterType.SLASH, true, lexer).match();
        new EnumToken(Transport.class, true, lexer, c -> via.setTransport((Transport) c)).match();
        new WordToken(true, lexer, via::setHost).match();
        new ComplexToken(false, lexer)
                .define(new CharacterToken(CharacterType.COLON, true, lexer))
                .define(new NumericToken(true, lexer, c -> via.setPort(c.intValue()))).match();
        new ParametersToken(false, lexer, via::setParameters).match();
        header.add(via);
        return header;
    }
}
