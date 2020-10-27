package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.Parametric;
import net.szvoc.xsip.sip.common.URI;

public class Contact extends Parametric<String> implements HeaderEntity {
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
