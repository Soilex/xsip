package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.Parameter;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ParametersToken extends Token<List<Parameter>> {
    public ParametersToken(String id, boolean required, StringBuffer lexer, Consumer<List<Parameter>> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public ParametersToken(boolean required, StringBuffer lexer, Consumer<List<Parameter>> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public ParametersToken(String id, boolean required, StringBuffer lexer) {
        super(id, required, lexer);
    }

    public ParametersToken(boolean required, StringBuffer lexer) {
        super(required, lexer);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        List<Parameter> parameters = new ArrayList<>();
        while (lexer.read(CharacterType.SEMICOLON) != null) {
            WordToken nameToken = new WordToken(true, lexer);
            nameToken.match();
            Parameter parameter = new Parameter();
            parameter.setName(nameToken.getValue());
            if (lexer.read(CharacterType.EQUALS) != null) {
                WordToken valueToken = new WordToken(true, lexer);
                valueToken.match();
                parameter.setValue(valueToken.getValue());
            }
            parameters.add(parameter);
        }
        this.setValue(parameters);
        return parameters.size() > 1;
    }
}
