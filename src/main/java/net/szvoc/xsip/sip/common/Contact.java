package net.szvoc.xsip.sip.common;

import lombok.Data;

@Data
public class Contact extends Parametric<String> {
    private String name;
    private URI uri;
    private String tag;
}
