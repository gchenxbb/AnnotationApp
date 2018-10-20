package chen.pa.com.annotions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
//运行时注解，作用范围是方法
//注解保留到java源码和class文件，会加载到虚拟机中
@Target(METHOD)
@Retention(RUNTIME)
public @interface AppAnnotMethod {
    String name() default "";
}
