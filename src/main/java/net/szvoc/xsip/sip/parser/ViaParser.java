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
        new ComplexToken(true, lexer, t -> header.add(via)) {
            @Override
            protected void rules() {
                rule(new EnumToken(Protocol.class, true, lexer, c -> via.setProtocol((Protocol) c)));
                rule(new CharacterToken(CharacterType.SLASH, true, lexer));
                rule(new WordToken(true, lexer, via::setVersion));
                rule(new CharacterToken(CharacterType.SLASH, true, lexer));
                rule(new EnumToken(Transport.class, true, lexer, c -> via.setTransport((Transport) c)));
                rule(new WordToken(true, lexer, via::setHost));
                rule(new ComplexToken(false, lexer) {
                    @Override
                    protected void rules() {
                        rule(new CharacterToken(CharacterType.COLON, true, lexer));
                        rule(new NumericToken(true, lexer, c -> via.setPort(c.intValue())));
                    }
                });
                rule(new ParametersToken(false, lexer, via::setParameters));
            }
        }.match();
        return header;
    }
}
