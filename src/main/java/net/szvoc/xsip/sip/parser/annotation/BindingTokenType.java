package net.szvoc.xsip.sip.parser.annotation;

import net.szvoc.xsip.sip.parser.internal.TokenType;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindingTokenType {
    TokenType value();
}
