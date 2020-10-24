package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.header.ContactHeader;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeaderName;
import net.szvoc.xsip.sip.parser.internal.*;

@BindingHeaderName(HeaderName.CONTACT)
public class ContactParser extends Parser<ContactHeader> {
    protected ContactParser() {
        super(DELIMITER_COLON);
    }

    @Override
    protected ContactHeader parse(Lexer lexer) throws SyntaxException {
        ContactHeader.Contact contact = new ContactHeader.Contact();
        if (CharacterType.DOUBLEQUOTE.isMatch(lexer.look())) {
            WordToken nameToken = lexer.skip(1).nextToken(TokenType.WORD);
            if (!CharacterType.DOUBLEQUOTE.isMatch(lexer.read())) {
                lexer.throwSyntaxException();
            }
            contact.setName(nameToken.getValue());
            lexer.skipBlank();
        }
        if (!CharacterType.LESS_THAN.isMatch(lexer.read())) {
            lexer.throwSyntaxException();
        }
        UriToken uriToken = lexer.nextToken(TokenType.URI);
        if (!CharacterType.GREATER_THAN.isMatch(lexer.read())) {
            lexer.throwSyntaxException();
        }
        contact.setUri(uriToken.getValue());

        ContactHeader header = new ContactHeader();
        header.add(contact);
        return header;
    }
}
