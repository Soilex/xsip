package net.szvoc.callcenter.sip.core.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SipHeader {
    @AliasFor("name")
    String[] value() default {};

    String[] name() default {};
}
