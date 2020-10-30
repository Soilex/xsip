package net.szvoc.xsip.sip.parser.annotation;

import net.szvoc.xsip.sip.header.HeaderName;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindingHeader {
    HeaderName[] value();
}
