package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.common.Parameter;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ParametersToken extends Token<List<Parameter>> {
    public ParametersToken(String id, boolean required, Lexer lexer, Consumer<List<Parameter>> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public ParametersToken(boolean required, Lexer lexer, Consumer<List<Parameter>> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public ParametersToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public ParametersToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        List<Parameter> parameters = new ArrayList<>();
        while (lexer.read(CharacterType.SEMICOLON) != null) {
            Parameter parameter = new Parameter();
            new WordToken(true, lexer, parameter::setName).match();
            if (lexer.read(CharacterType.EQUALS) != null) {
                new WordToken(true, lexer, parameter::setValue).match();
            }
            parameters.add(parameter);
        }
        this.setValue(parameters);
        return parameters.size() > 0;
    }
}
