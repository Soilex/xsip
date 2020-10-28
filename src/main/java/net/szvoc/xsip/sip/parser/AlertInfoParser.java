package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.AlertInfo;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.ComplexToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.StringToken;

@BindingHeader(HeaderName.ALERT_INFO)
public class AlertInfoParser extends Parser<AlertInfo> {
    @Override
    protected Header<AlertInfo> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<AlertInfo> header = new Header<>(headerName);
        AlertInfo alertInfo = new AlertInfo();
        new ComplexToken(true, lexer, t -> header.add(alertInfo))
                .define(new StringToken(false, lexer, alertInfo::setUri).startWith(CharacterType.LESS_THAN).endWith(CharacterType.GREATER_THAN))
                .define(new StringToken(false, lexer, alertInfo::setMessage))
                .match();
        return header;
    }
}
