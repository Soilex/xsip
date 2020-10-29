package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.ParameterName;
import net.szvoc.xsip.sip.common.Parametric;

public class Authorization extends Parametric<String> {
    @Getter
    @Setter
    private String scheme;

    public String getRealm() {
        return this.getParameterValue(ParameterName.REALM, null);
    }

    public void setRealm(String value) {
        this.setParameter(ParameterName.REALM, value);
    }

    public String getUserName() {
        return this.getParameterValue(ParameterName.USERNAME, null);
    }

    public void setUserName(String value) {
        this.setParameter(ParameterName.USERNAME, value);
    }

    public String getNonce() {
        return this.getParameterValue(ParameterName.NONCE, null);
    }

    public void setNonce(String value) {
        this.setParameter(ParameterName.NONCE, value);
    }

    public String getUri() {
        return this.getParameterValue(ParameterName.URI, null);
    }

    public void setUri(String value) {
        this.setParameter(ParameterName.URI, value);
    }

    public String getResponse() {
        return this.getParameterValue(ParameterName.RESPONSE, null);
    }

    public void setResponse(String value) {
        this.setParameter(ParameterName.RESPONSE, value);
    }

    public String getAlgorithm() {
        return this.getParameterValue(ParameterName.ALGORITHM, null);
    }

    public void setAlgorithm(String value) {
        this.setParameter(ParameterName.ALGORITHM, value);
    }

    public String getcNonce() {
        return this.getParameterValue(ParameterName.CNONCE, null);
    }

    public void setcNonce(String value) {
        this.setParameter(ParameterName.CNONCE, value);
    }

    public String getOpaque() {
        return this.getParameterValue(ParameterName.OPAQUE, null);
    }

    public void setOpaque(String value) {
        this.setParameter(ParameterName.OPAQUE, value);
    }

    public String getQop() {
        return this.getParameterValue(ParameterName.QOP, null);
    }

    public void setQop(String value) {
        this.setParameter(ParameterName.QOP, value);
    }

    public int getNonceCount() {
        return this.getParameterValue(ParameterName.NONCE_COUNT, 0);
    }

    public void setNonceCount(int value) {
        this.setParameter(ParameterName.NONCE_COUNT, String.format("%08d", value));
    }
}
