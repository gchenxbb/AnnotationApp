package chen.pa.com.annotationapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import chen.pa.com.annotions.AppAnnotClass;
import chen.pa.com.annotions.AppAnnotField;
import chen.pa.com.annotions.AppAnnotMethod;
//编译时注解
@AppAnnotClass(author = "chen",date = "2018-10-18")
public class MainActivity extends Activity {
    public static final String TAG = "AppAnotMainActivity";

    @AppAnnotField(desc="desc1")
    String descStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printAnno();
    }

    @AppAnnotMethod(name="chen")
    private void init() {
    }

    //运行时打印
    private void printAnno() {
        Class clz = this.getClass();
        Method [] methods = clz.getDeclaredMethods();
        for(Method method:methods){
            AppAnnotMethod appAnnot = method.getAnnotation(AppAnnotMethod.class);
            if (appAnnot != null) {
                Log.d(TAG, "method:" + method.getName() + ",name:" + appAnnot.name());
                //打印结果method:init,name:chen
            }
        }
        try {
            Field field = clz.getField("descStr");
            AppAnnotField appAnnot = field.getAnnotation(AppAnnotField.class);
            if (appAnnot != null) {
                Log.d(TAG, "field:" + field.getName() + ",desc:" + appAnnot.desc());
                //打印结果field:descStr,desc:desc1
            }
        } catch (NoSuchFieldException e) {
        }

    }
}
