package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.ParameterName;
import net.szvoc.xsip.sip.common.Parametric;
import net.szvoc.xsip.sip.common.URI;

public class Contact extends Parametric<String> {
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
}
