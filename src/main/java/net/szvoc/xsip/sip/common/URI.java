package net.szvoc.xsip.sip.common;

import lombok.Data;

@Data
public class URI extends Parametric<String> {
    private String schema;
    private String user;
    private String host;
    private int port;
}
