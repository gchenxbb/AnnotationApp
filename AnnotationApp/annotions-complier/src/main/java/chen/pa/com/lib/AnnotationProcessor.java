package chen.pa.com.lib;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import chen.pa.com.annotions.AppAnnotClass;

//注解处理器
//该类会打成jar包，编译时调用，编译器会自动查找所有继承AbstractProcessor的类，触发process方法
//在外部设置resources目录，文件中注册该处理器，现在用AutoService代替注册
//仅在编译时使用，不会打包进apk

@AutoService(Processor.class)
@SupportedAnnotationTypes("chen.pa.com.annotions.AppAnnotClass")//指定支持的注解类路径
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AnnotationProcessor extends AbstractProcessor {
    private static HashMap<String, String> hashMap=new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //https://blog.csdn.net/industriously/article/details/53932425
        //https://www.jianshu.com/p/50d95fbf635c
        System.out.println("start in AnnotationProcessor.process");
        StringBuilder builder = new StringBuilder()
                .append("package chen.pa.com.lib;\n\n")
                .append("public class HelloWorld {\n\n")
                .append("\tpublic String sayHelloWorld() {\n\n")
                .append("\t\treturn \"");
        for (TypeElement e : set) {
            for (Element element : roundEnvironment.getElementsAnnotatedWith(e)) {
                //被指定注解的元素集合
                AppAnnotClass appAnnotClass = element.getAnnotation(AppAnnotClass.class);
                String name = element.getSimpleName().toString();//@注解修饰的类的名称
                if(appAnnotClass!=null){
                    String author=appAnnotClass.author();
                    hashMap.put(element.getEnclosingElement().toString(), author);
                    System.out.println("className:" + name+",author:"+author);
                    builder.append(name);
                    builder.append(",");
                    builder.append(author);
                    builder.append(",");
                }
            }
        }
        builder.append("hello world,annotation!\";\n")
                .append("\t}\n")
                .append("}\n");
        //生成一个java文件
        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile("chen.pa.com.lib.HelloWorld");

            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }
        System.out.println("end in AnnotationProcessor.process");
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
