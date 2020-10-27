package net.szvoc.xsip.sip.common;

import lombok.Getter;
import lombok.Setter;

public class Contact extends Parametric<String> {
    private static final String TAG = "tag";

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private URI uri;

    private String tag;

    public String getTag() {
        return this.getParameterValue(TAG, null);
    }

    public void setTag(String value) {
        this.setParameter(TAG, value);
    }
}
