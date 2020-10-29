package net.szvoc.xsip.sip.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class URI extends Parametric<String> {
    private String scheme;
    private String user;
    private String host;
    private int port;
}
