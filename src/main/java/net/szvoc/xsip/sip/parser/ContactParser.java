package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.ContactHeader;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeaderName;
import net.szvoc.xsip.sip.parser.internal.*;
import net.szvoc.xsip.sip.parser.internal.Character;

@BindingHeaderName(HeaderName.CONTACT)
public class ContactParser extends Parser<ContactHeader> {
    protected ContactParser() {
        super(DELIMITER_COLON);
    }

    @Override
    protected ContactHeader parse(Lexer lexer) throws SyntaxException {
        ContactHeader.Contact contact = new ContactHeader.Contact();
        if (Character.DOUBLEQUOTE.isMatch(lexer.look())) {
            WordToken nameToken = lexer.skip(1).nextToken(TokenType.WORD);
            if (!Character.DOUBLEQUOTE.isMatch(lexer.read())) {
                lexer.throwSyntaxException();
            }
            contact.setName(nameToken.getValue().get());
            lexer.skipBlank();
        }
        if (!Character.LESS_THAN.isMatch(lexer.read())) {
            lexer.throwSyntaxException();
        }
        UriToken uriToken = lexer.nextToken(TokenType.URI);
        if (!Character.GREATER_THAN.isMatch(lexer.read())) {
            lexer.throwSyntaxException();
        }
        contact.setUri(uriToken.getValue().get());

        ContactHeader header = new ContactHeader();
        header.add(contact);
        return header;
    }
}
