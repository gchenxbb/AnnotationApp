package chen.pa.com.annotions;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//定义一个运行时注解，作用范围是变量
@Documented
@Target({ FIELD, })
@Retention(RUNTIME)
public @interface AppAnnotField {
    String desc() default "I am a Filed注解";;
}
