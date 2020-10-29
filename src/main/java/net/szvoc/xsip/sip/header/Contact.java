package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.ParameterName;
import net.szvoc.xsip.sip.common.Parametric;
import net.szvoc.xsip.sip.common.URI;

public class Contact extends Parametric<String> {
    private static float DEFAULT_Q = 1f;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private URI uri;

    public String getTag() {
        return this.getParameterValue(ParameterName.TAG, null);
    }

    public void setTag(String value) {
        this.setParameter(ParameterName.TAG, value);
    }

    public float getQ() {
        return this.getParameterValue(ParameterName.Q, DEFAULT_Q);
    }

    public void setQ(float value) {
        this.setParameter(ParameterName.Q, value);
    }

    public int getExpires() {
        return this.getParameterValue(ParameterName.EXPIRES, 0);
    }

    public void setExpires(int value) {
        this.setParameter(ParameterName.EXPIRES, value);
    }
}
