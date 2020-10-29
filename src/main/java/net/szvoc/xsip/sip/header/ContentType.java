package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.ParameterName;
import net.szvoc.xsip.sip.common.Parametric;

public class ContentType extends Parametric<String> {
    private static final float DEFAULT_Q_VALUE = 1f;

    @Getter
    @Setter
    private String mainType;

    @Getter
    @Setter
    private String subType;

    public float getQValue() {
        return this.getParameterValue(ParameterName.Q_VALUE, DEFAULT_Q_VALUE);
    }

    public void setQValue(float value) {
        this.setParameter(ParameterName.Q_VALUE, value);
    }

    public boolean isAllowsAllTypes() {
        return "*".equals(mainType);
    }

    public boolean isAllowsAllSubtypes() {
        return "*".equals(subType);
    }
}
