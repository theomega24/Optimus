package me.notom3ga.optimus.check.setup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CheckInfo {
    String name();
    String type();
    Category category();
    String[] packets();
    boolean experimental() default false;
}
