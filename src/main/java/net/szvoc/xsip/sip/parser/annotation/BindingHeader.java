package net.szvoc.xsip.sip.parser.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindingHeader {
    String[] value();
}
