package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.Lexer;
import net.szvoc.xsip.sip.parser.internal.WordToken;

/**
 * Call-ID:
 * The Call-ID header field uniquely identifies a particular invitation
 * or all registrations of a particular client.  A single multimedia
 * conference can give rise to several calls with different Call-IDs,
 * for example, if a user invites a single individual several times to
 * the same (long-running) conference.  Call-IDs are case-sensitive and
 * are simply compared byte-by-byte.
 * Examples:
 * Call-ID: f81d4fae-7dec-11d0-a765-00a0c91e6bf6@biloxi.com
 * <p>
 * Content-Encoding:
 * The Content-Encoding header field is used as a modifier to the
 * "media-type".  When present, its value indicates what additional
 * content codings have been applied to the entity-body, and thus what
 * decoding mechanisms MUST be applied in order to obtain the media-type
 * referenced by the Content-Type header field.  Content-Encoding is
 * primarily used to allow a body to be compressed without losing the
 * identity of its underlying media type.
 * If multiple encodings have been applied to an entity-body, the
 * content codings MUST be listed in the order in which they were
 * applied.
 * All content-coding values are case-insensitive.  IANA acts as a
 * registry for content-coding value tokens.  See [H3.5] for a
 * definition of the syntax for content-coding.
 * Clients MAY apply content encodings to the body in requests.  A
 * server MAY apply content encodings to the bodies in responses.  The
 * server MUST only use encodings listed in the Accept-Encoding header
 * field in the request.
 * The compact form of the Content-Encoding header field is e.
 * Examples:
 * Content-Encoding: gzip
 */
@BindingHeader({HeaderName.CALL_ID, HeaderName.CONTENT_ENCODING})
public class StringParser extends Parser<String> {
    @Override
    protected Header<String> doParse(String headerName, Lexer lexer) throws SyntaxException {
        Header<String> header = new Header<>(headerName);
        new WordToken(true, lexer, header::add).match();
        return header;
    }
}
