package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.ContentType;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.*;

/**
 * The Accept header field follows the syntax defined in [H14.1].  The
 * semantics are also identical, with the exception that if no Accept
 * header field is present, the server SHOULD assume a default value of
 * application/sdp.
 * <p>
 * An empty Accept header field means that no formats are acceptable.
 * <p>
 * Example:
 * Accept: application/sdp;level=1, application/x-private, text/html
 */
@BindingHeader(HeaderName.ACCEPT)
public class AcceptParser extends Parser<ContentType> {
    @Override
    protected Header<ContentType> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<ContentType> header = new Header<>(headerName);
        resolve(header, lexer, () -> {
            ContentType contentType = new ContentType();
            new WordToken(true, lexer, contentType::setMainType).match();
            new CharacterToken(true, lexer).expect(CharacterType.SLASH).match();
            new WordToken(true, lexer, contentType::setSubType).match();
            new ParametersToken(false, lexer, contentType::setParameters).match();
            header.add(contentType);
        });
        return header;
    }
}
