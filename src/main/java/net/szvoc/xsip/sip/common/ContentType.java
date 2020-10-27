package net.szvoc.xsip.sip.common;

import lombok.Getter;
import lombok.Setter;

public class ContentType extends Parametric<String> {
    private static final String Q_VALUE = "q-value";
    private static final float DEFAULT_Q_VALUE = 1f;

    @Getter
    @Setter
    private String contentType;

    @Getter
    @Setter
    private String contentSubType;

    public float getQValue() {
        return this.getParameterValue(Q_VALUE, DEFAULT_Q_VALUE);
    }

    public void setQValue(float value) {
        this.setParameter(Q_VALUE, value);
    }
}
