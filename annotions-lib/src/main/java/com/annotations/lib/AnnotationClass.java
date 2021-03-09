package com.annotations.lib;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 运行时注解，作用范围是类
 */
@Target(TYPE)
@Retention(RUNTIME)//
public @interface AnnotationClass {
    String authorName();
    String createTime();
}
