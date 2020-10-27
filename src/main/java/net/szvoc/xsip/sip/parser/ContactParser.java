package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.Contact;
import net.szvoc.xsip.sip.header.Header;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import net.szvoc.xsip.sip.parser.internal.ContactToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;

@BindingHeader({HeaderName.CONTACT, HeaderName.TO, HeaderName.FROM})
public class ContactParser extends Parser<Contact> {
    @Override
    protected Header<Contact> doParse(String headerName, Lexer lexer) throws SyntaxException {
        ContactToken contactToken = new ContactToken(true, lexer);
        contactToken.match();
        Header<Contact> header = new Header<>(headerName);
        header.add(contactToken.getValue());
        return header;
    }
}
