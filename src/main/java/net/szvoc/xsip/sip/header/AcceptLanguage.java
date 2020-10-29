package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.ParameterName;
import net.szvoc.xsip.sip.common.Parametric;

public class AcceptLanguage extends Parametric<String> {
    private static float DEFAULT_Q = 1f;

    @Getter
    @Setter
    private String language;

    @Getter
    @Setter
    private String country;

    public float getQ() {
        return this.getParameterValue(ParameterName.Q, DEFAULT_Q);
    }

    public void setQ(float value) {
        this.setParameter(ParameterName.Q, value);
    }
}
