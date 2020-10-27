package net.szvoc.xsip.sip.header;

import net.szvoc.xsip.sip.common.Contact;

public class ContactHeader extends Header<Contact> {
    @Override
    public String getName() {
        return HeaderName.CONTACT;
    }
}
