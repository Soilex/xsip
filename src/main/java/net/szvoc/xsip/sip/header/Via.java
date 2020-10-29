package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.*;

public class Via extends Parametric<String> {
    @Getter
    @Setter
    private EnumEx<Protocol> protocol;

    @Getter
    @Setter
    private String version;

    @Getter
    @Setter
    private EnumEx<Transport> transport;

    @Getter
    @Setter
    private String host;

    @Getter
    @Setter
    private int port;

    public String getBranch() {
        return this.getParameterValue(ParameterName.BRANCH, null);
    }

    public void setBranch(String value) {
        this.setParameter(ParameterName.BRANCH, value);
    }
}
