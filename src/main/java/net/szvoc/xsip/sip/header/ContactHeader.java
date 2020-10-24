package net.szvoc.xsip.sip.header;

import lombok.Data;
import net.szvoc.xsip.sip.common.URI;

public class ContactHeader extends Header<ContactHeader.Contact> {
    @Override
    public String getName() {
        return HeaderName.CONTACT;
    }

    @Data
    public static class Contact {
        private String name;
        private URI uri;
    }
}
