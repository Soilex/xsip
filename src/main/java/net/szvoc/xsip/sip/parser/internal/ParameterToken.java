package net.szvoc.xsip.sip.parser.internal;

import net.szvoc.xsip.sip.common.Parameter;
import net.szvoc.xsip.sip.parser.SyntaxException;
import net.szvoc.xsip.sip.parser.annotation.BindingTokenType;

@BindingTokenType(TokenType.PARAMETER)
public class ParameterToken extends Token<Parameter> {
    protected ParameterToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    public void scan() throws SyntaxException {
        super.scan();

        if (!Character.SEMICOLON.isMatch(lexer.read())) {
            lexer.throwSyntaxException();
        }
        WordToken parameterNameToken = lexer.nextToken(TokenType.WORD);
        Parameter parameter = new Parameter();
        parameter.setName(parameterNameToken.getValue().get());
        if (Character.EQUALS.isMatch(lexer.look())) {
            lexer.skip(1);
            WordToken parameterValueToken = lexer.nextToken(TokenType.WORD);
            parameter.setValue(parameterValueToken.getValue().get());
        }
        this.setValue(parameter);
    }
}
