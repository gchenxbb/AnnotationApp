package com.pa.annotions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//编译时注解，
//作用范围是类
@Target(TYPE)
@Retention(CLASS)//
public @interface AnnotationClass {
    String authorName();
    String createTime();
}
