package chen.pa.com.annotions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

//编译时注解
@Target(TYPE)
@Retention(CLASS)//
public @interface AppAnnotClass {
    String author();
    String date();
}
