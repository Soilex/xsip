package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.Parameter;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.util.ArrayList;
import java.util.List;

public class ParametersToken extends Token<List<Parameter>> {
    public ParametersToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected void doScan() throws SyntaxException {
        List<Parameter> parameters = new ArrayList<>();
        while (Character.SEMICOLON.isMatch(lexer.read())) {
            lexer.skipBlank();
            WordToken nameToken = new WordToken(true, lexer);
            nameToken.scan();
            Parameter parameter = new Parameter();
            parameter.setName(nameToken.getValue().get());
            if (Character.EQUALS.isMatch(lexer.peek())) {
                lexer.skip(1);
                WordToken valueToken = new WordToken(true, lexer);
                valueToken.scan();
                parameter.setValue(valueToken.getValue().get());
            }
            parameters.add(parameter);
        }
        this.setValue(parameters);
    }
}
