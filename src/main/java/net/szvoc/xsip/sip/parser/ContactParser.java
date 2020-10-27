package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.ContactHeader;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeaderName;
import net.szvoc.xsip.sip.parser.internal.ContactToken;
import net.szvoc.xsip.sip.parser.internal.Lexer;

@BindingHeaderName(HeaderName.CONTACT)
public class ContactParser extends Parser<ContactHeader> {
    @Override
    protected ContactHeader doParse(Lexer lexer) throws SyntaxException {
        ContactToken contactToken = new ContactToken(true, lexer);
        contactToken.match();
        ContactHeader header = new ContactHeader();
        header.add(contactToken.getValue());
        return header;
    }
}
