package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.header.ContentType;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.CharacterToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.ParametersToken;
import net.szvoc.xsip.sip.parser.internal.WordToken;

/**
 * The Content-Type header field indicates the media type of the
 * message-body sent to the recipient.  The "media-type" element is
 * defined in [H3.7].  The Content-Type header field MUST be present if
 * the body is not empty.  If the body is empty, and a Content-Type
 * header field is present, it indicates that the body of the specific
 * type has zero length (for example, an empty audio file).
 * The compact form of the header field is c.
 * Examples
 * Content-Type: application/sdp;charset=utf8
 */
@BindingHeader(HeaderName.CONTENT_TYPE)
public class ContentTypeParser extends Parser<ContentType> {
    @Override
    protected Header<ContentType> doParse(EnumEx<HeaderName> headerName, Lexer lexer) throws SyntaxException {
        Header<ContentType> header = new Header<>(headerName);
        ContentType contentType = new ContentType();
        new WordToken(true, lexer, contentType::setMainType).match();
        new CharacterToken(true, lexer).expect(CharacterType.SLASH).match();
        new WordToken(true, lexer, contentType::setSubType).match();
        new ParametersToken(false, lexer, contentType::setParameters).match();
        header.add(contentType);
        return header;
    }
}
