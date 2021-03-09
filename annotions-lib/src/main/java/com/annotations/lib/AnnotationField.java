package com.annotations.lib;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 运行时注解，作用范围是变量
 */
@Documented
@Target({ FIELD, })
@Retention(RUNTIME)
public @interface AnnotationField {
    String descInfo() default "默认：我是Filed注解";
}
