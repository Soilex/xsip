package net.szvoc.xsip.sip.common;

import lombok.Data;

@Data
public class MediaType {
    private String contentType;
    private String contentSubType;

    public boolean isAllowsAllContentSubTypes() {
        return "*".equals(contentSubType);
    }

    public boolean isAllowsAllContentTypes() {
        return "*".equals(contentType);
    }
}
