package net.szvoc.xsip.sip.parser.internal;

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
    protected void doMatch() throws SyntaxException {
        List<Parameter> parameters = new ArrayList<>();
        while (Character.SEMICOLON.isMatch(lexer.read())) {
            lexer.skipBlank();
            WordToken nameToken = new WordToken(true, lexer);
            nameToken.match();
            Parameter parameter = new Parameter();
            parameter.setName(nameToken.getValue());
            if (Character.EQUALS.isMatch(lexer.peek())) {
                lexer.skip(1);
                WordToken valueToken = new WordToken(true, lexer);
                valueToken.match();
                parameter.setValue(valueToken.getValue());
            }
            parameters.add(parameter);
        }
        this.setValue(parameters);
    }
}
