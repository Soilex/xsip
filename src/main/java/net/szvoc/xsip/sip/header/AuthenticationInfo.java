package net.szvoc.xsip.sip.header;

import net.szvoc.xsip.sip.common.ParameterName;
import net.szvoc.xsip.sip.common.Parametric;

public class AuthenticationInfo extends Parametric<String> {
    public String getNextNonce() {
        return this.getParameterValue(ParameterName.NEXT_NONCE, null);
    }

    public void setNextNonce(String value) {
        this.setParameter(ParameterName.NEXT_NONCE, value);
    }

    public String getQop() {
        return this.getParameterValue(ParameterName.QOP, null);
    }

    public void setQop(String value) {
        this.setParameter(ParameterName.QOP, value);
    }

    public String getCNonce() {
        return this.getParameterValue(ParameterName.CNONCE, null);
    }

    public void setCNonce(String value) {
        this.setParameter(ParameterName.CNONCE, value);
    }

    public int getNonceCount() {
        return this.getParameterValue(ParameterName.NONCE_COUNT, 0);
    }

    public void setNonceCount(int value) {
        this.setParameter(ParameterName.NONCE_COUNT, value);
    }

    public String getResponse() {
        return this.getParameterValue(ParameterName.RESPONSE, null);
    }

    public void setResponse(String value) {
        this.setParameter(ParameterName.RESPONSE, value);
    }
}
