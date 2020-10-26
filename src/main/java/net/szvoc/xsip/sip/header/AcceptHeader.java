package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.Parametric;

/**
 * This interface represents an Accept request-header. It can be used in to
 * specify media-ranges which are acceptable for the response. AcceptHeaders
 * can be used to indicate that the request is specifically limited to a small
 * set of desired types. The specification of the acceptable media
 * is split into type and subtype.
 * <p>
 * An AcceptHeader may be followed by one or more parameters applicable to the
 * media-range. q-values allow the user to indicate the relative degree of
 * preference for that media-range, using the q-value scale from 0 to 1. (If no
 * q-value is present, the media-range should be treated as having a q-value of
 * 1.)
 * <p>
 * If no AcceptHeader is present in a Request, the server SHOULD assume a media
 * of type "application" and subType
 * "sdp". If an AcceptHeader is present, and if the server cannot send a
 * response which is acceptable according to the combined Accept field value,
 * then the server should send a Response message with a NOT_ACCEPTABLE
 * status code.
 * <p>
 * For example:<br>
 * <code>Accept: application/sdp;q-value=0.5, application/x-private, text/html</code>
 *
 * @author BEA Systems, NIST
 * @version 1.2
 */
public class AcceptHeader extends Header<AcceptHeader.ContentType> {
    @Override
    public String getName() {
        return HeaderName.ACCEPT;
    }

    public static class ContentType extends Parametric<String> {
        private static final String Q_VALUE = "q-value";

        private float qValue = 1.0f;

        @Getter
        @Setter
        private String contentType;

        @Getter
        @Setter
        private String contentSubType;

        public float getQValue() {
            return this.getParameter(Q_VALUE).getFloat();
        }
    }
}
