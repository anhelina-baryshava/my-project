package org.example;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})// annotation for method and class
@Retention(RetentionPolicy.RUNTIME)// level of accessibility for annotation
@Tag("smoke")
public @interface Smoke {
}
