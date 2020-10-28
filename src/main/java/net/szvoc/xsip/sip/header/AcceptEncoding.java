package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.Parametric;

public class AcceptEncoding extends Parametric<String> {
    private static final String Q_VALUE = "q";
    private static float DEFAULT_Q_VALUE = 1f;

    @Getter
    @Setter
    private String encoding;

    public float getQValue() {
        return this.getParameterValue(Q_VALUE, DEFAULT_Q_VALUE);
    }

    public void setQValue(float value) {
        this.setParameter(Q_VALUE, value);
    }
}
