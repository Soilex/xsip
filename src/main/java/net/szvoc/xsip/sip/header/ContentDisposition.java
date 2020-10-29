package net.szvoc.xsip.sip.header;

import lombok.Getter;
import lombok.Setter;
import net.szvoc.xsip.sip.common.Parametric;

public class ContentDisposition extends Parametric<String> {
    @Getter
    @Setter
    private String dispositionType;
}
