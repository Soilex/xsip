package net.szvoc.callcenter.sip.core.header;

import lombok.*;
import net.szvoc.callcenter.sip.core.Field;
import net.szvoc.callcenter.sip.core.Header;
import net.szvoc.callcenter.sip.core.HeaderName;
import net.szvoc.callcenter.sip.core.annotation.SipHeader;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader({HeaderName.AUTHORIZATION, HeaderName.WWW_AUTHENTICATE})
public class AuthorizationHeader extends Header {
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_REALM = "realm";
    private static final String FIELD_NONCE = "nonce";
    private static final String FIELD_URI = "uri";
    private static final String FIELD_RESPONSE = "response";
    private static final String FIELD_ALGORITHM = "algorithm";
    private static final String FIELD_QOP = "qop";
    private static final String FIELD_CNONCE = "cnonce";
    private static final String FIELD_NC = "nc";
    private static final String FIELD_OPAQUE = "opaque";

    private String scheme;

    private String username;
    private String realm;
    private String nonce;
    private String uri;
    private String response;
    private String algorithm;
    private String qop;
    private String cnonce;
    private String nc;
    private String opaque;

    protected AuthorizationHeader(String name, String scheme, String username, String realm, String nonce, String uri, String response, String algorithm, String qop, String cnonce, String nc, String opaque) {
        super(name, generateValue(scheme, username, realm, nonce, uri, response, algorithm, qop, cnonce, nc, opaque));
        this.scheme = scheme;
        this.username = username;
        this.realm = realm;
        this.nonce = nonce;
        this.uri = uri;
        this.response = response;
        this.algorithm = algorithm;
        this.qop = qop;
        this.cnonce = cnonce;
        this.nc = nc;
        this.opaque = opaque;
    }

    private static String generateValue(String scheme, String username, String realm, String nonce, String uri, String response, String algorithm, String qop, String cnonce, String nc, String opaque) {
        var stringBuilder = new StringBuilder().append(scheme).append(" ");
        if (Strings.isNotBlank(username)) {
            stringBuilder.append(FIELD_USERNAME).append("=").append("\"").append(username).append("\"").append(", ");
        }
        if (Strings.isNotBlank(realm)) {
            stringBuilder.append(FIELD_REALM).append("=").append("\"").append(realm).append("\"").append(", ");
        }
        if (Strings.isNotBlank(nonce)) {
            stringBuilder.append(FIELD_NONCE).append("=").append("\"").append(nonce).append("\"").append(", ");
        }
        if (Strings.isNotBlank(uri)) {
            stringBuilder.append(FIELD_URI).append("=").append("\"").append(uri).append("\"").append(", ");
        }
        if (Strings.isNotBlank(response)) {
            stringBuilder.append(FIELD_RESPONSE).append("=").append("\"").append(response).append("\"").append(", ");
        }
        if (Strings.isNotBlank(algorithm)) {
            stringBuilder.append(FIELD_ALGORITHM).append("=").append(algorithm).append(", ");
        }
        if (Strings.isNotBlank(qop)) {
            stringBuilder.append(FIELD_QOP).append("=").append(qop).append(", ");
        }
        if (Strings.isNotBlank(cnonce)) {
            stringBuilder.append(FIELD_CNONCE).append("=").append("\"").append(cnonce).append("\"").append(", ");
        }
        if (Strings.isNotBlank(nc)) {
            stringBuilder.append(FIELD_NC).append("=").append(nc).append(", ");
        }
        if (Strings.isNotBlank(opaque)) {
            stringBuilder.append(FIELD_OPAQUE).append("=").append("\"").append(opaque).append("\"").append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    @Override
    protected void load(String nameAndValue) {
        super.load(nameAndValue);

        var index = value().indexOf(' ');
        this.scheme = value().substring(0, index);
        Arrays.stream(value().substring(index + 1).split("\\s*,\\s*"))
                .forEach(c -> {
                    if (!StringUtils.isEmpty(c)) {
                        var field = Field.parse(c);
                        if (field.getName().equalsIgnoreCase(FIELD_USERNAME)) {
                            this.username = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_REALM)) {
                            this.realm = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_NONCE)) {
                            this.nonce = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_URI)) {
                            this.uri = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_RESPONSE)) {
                            this.response = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_ALGORITHM)) {
                            this.algorithm = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_QOP)) {
                            this.qop = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_CNONCE)) {
                            this.cnonce = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_NC)) {
                            this.nc = field.getValue();
                        } else if (field.getName().equalsIgnoreCase(FIELD_OPAQUE)) {
                            this.opaque = field.getValue();
                        }
                    }
                });
    }

    public static class Builder {
        private String headerName = HeaderName.AUTHORIZATION;
        private String scheme;
        private String username, realm, nonce, uri, response, algorithm, qop, cnonce, nc, opaque;

        public Builder www(boolean value) {
            this.headerName = value ? HeaderName.WWW_AUTHENTICATE : HeaderName.AUTHORIZATION;
            return this;
        }

        public Builder scheme(String value) {
            this.scheme = value;
            return this;
        }

        public Builder username(String value) {
            this.username = value;
            return this;
        }

        public Builder realm(String value) {
            this.realm = value;
            return this;
        }

        public Builder nonce(String value) {
            this.nonce = value;
            return this;
        }

        public Builder uri(String value) {
            this.uri = value;
            return this;
        }

        public Builder response(String value) {
            this.response = value;
            return this;
        }

        public Builder algorithm(String value) {
            this.algorithm = value;
            return this;
        }

        public Builder qop(String value) {
            this.qop = value;
            return this;
        }

        public Builder cnonce(String value) {
            this.cnonce = value;
            return this;
        }

        public Builder nc(String value) {
            this.nc = value;
            return this;
        }

        public Builder opaque(String value) {
            this.opaque = value;
            return this;
        }

        public AuthorizationHeader build() {
            return new AuthorizationHeader(headerName, scheme, username, realm, nonce, uri, response, algorithm, qop, cnonce, nc, opaque);
        }
    }
}
