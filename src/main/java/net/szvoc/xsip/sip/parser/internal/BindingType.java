package net.szvoc.xsip.sip.parser.internal;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindingType {
    TokenType value();
}
