package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.Parametric;
import net.szvoc.xsip.sip.common.Protocol;
import net.szvoc.xsip.sip.common.Transport;

public class Via extends Parametric<String> {
    private static final String BRANCH = "branch";

    @Getter
    @Setter
    private Protocol protocol;

    @Getter
    @Setter
    private String version;

    @Getter
    @Setter
    private Transport transport;

    @Getter
    @Setter
    private String host;

    @Getter
    @Setter
    private int port;

    private String branch;

    public String getBranch() {
        return this.getParameterValue(BRANCH, null);
    }

    public void setBranch(String value) {
        this.setParameter(BRANCH, value);
    }
}
