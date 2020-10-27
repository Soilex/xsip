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
        final Contact contact = new Contact();
        return new ComplexToken(isRequired(), this.lexer, t -> this.setValue(contact)) {
            @Override
            protected void rules() {
                // name
                rule(new ComplexToken(false, this.lexer) {
                    @Override
                    protected void rules() {
                        rule(new CharacterToken(CharacterType.DOUBLEQUOTE, true, this.lexer));
                        rule(new WordToken(true, this.lexer, contact::setName));
                        rule(new CharacterToken(CharacterType.DOUBLEQUOTE, true, this.lexer));
                    }
                });
                // uri
                rule(new ComplexToken(true, this.lexer) {
                    @Override
                    protected void rules() {
                        rule(new CharacterToken(CharacterType.LESS_THAN, true, this.lexer));
                        rule(new UriToken(true, this.lexer, contact::setUri));
                        rule(new CharacterToken(CharacterType.GREATER_THAN, true, this.lexer));
                    }
                });
                // parameters
                rule(new ParametersToken(false, this.lexer, contact::setParameters));
            }
        }.match();
    }
}
