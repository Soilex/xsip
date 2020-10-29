package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.header.Contact;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.function.Consumer;

public class ContactToken extends Token<Contact> {

    public ContactToken(String id, boolean required, Lexer lexer, Consumer<Contact> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public ContactToken(boolean required, Lexer lexer, Consumer<Contact> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public ContactToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public ContactToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        String text = "Contact: \"sonic\" <sip:1001@127.0.0.1:61917;ob;received=192.168.1.2>;tag=123456789\r\n";
        final Contact contact = new Contact();
        return new ComplexToken(isRequired(), this.lexer, t -> this.setValue(contact))
                // name
                .define(new StringToken(false, this.lexer, contact::setName)
                        .startWith(CharacterType.DOUBLEQUOTE)
                        .endWith(CharacterType.DOUBLEQUOTE))
                // uri
                .define(new CharacterToken(true, this.lexer).expect(CharacterType.LESS_THAN))
                .define(new UriToken(true, this.lexer, contact::setUri))
                .define(new CharacterToken(true, this.lexer).expect(CharacterType.GREATER_THAN))
                // parameters
                .define(new ParametersToken(false, this.lexer, contact::setParameters))
                .match();
    }
}
