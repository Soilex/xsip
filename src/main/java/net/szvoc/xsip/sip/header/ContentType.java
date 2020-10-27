package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.Parametric;

public class ContentType extends Parametric<String> {
    private static final String Q_VALUE = "q-value";
    private static final float DEFAULT_Q_VALUE = 1f;

    @Getter
    @Setter
    private String mainType;

    @Getter
    @Setter
    private String subType;

    public float getQValue() {
        return this.getParameterValue(Q_VALUE, DEFAULT_Q_VALUE);
    }

    public void setQValue(float value) {
        this.setParameter(Q_VALUE, value);
    }

    public boolean isAllowsAllTypes() {
        return "*".equals(mainType);
    }

    public boolean isAllowsAllSubtypes() {
        return "*".equals(subType);
    }
}
