package com.pa.annotationapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pa.annotationprocessor.generated.GeneratedClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.pa.annotions.*;

import chen.pa.com.annotationapp.R;

@AnnotationClass(authorName = "chen", createTime = "2019年")
public class MainActivity extends Activity {
    public static final String TAG = "MainActivityTestAppAnot";

    @AnnotationField(descInfo = "成功信息")
    String mSuccessMsg;

    @BindViews(R.id.tv_message)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_message);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneratedClass generatedClass = new GeneratedClass();
                String info = generatedClass.getMessage();
                Toast.makeText(MainActivity.this, "info:" + info, Toast.LENGTH_SHORT).show();
            }
        });
        printAnno();
    }

    @AnnotationMethod(name = "initView方法")
    private void initView(Context context) {
    }

    //运行时打印
    private void printAnno() {
        try {
            Method method = MainActivity.class.getDeclaredMethod("initView", Context.class);
            AnnotationMethod appAnnot = method.getAnnotation(AnnotationMethod.class);
            if (appAnnot != null) {
                Toast.makeText(this, "method:" + method.getName() + ",name:" + appAnnot.name(), Toast.LENGTH_SHORT).show();
            }
        } catch (NoSuchMethodException e) {
        }

        try {
            Field field = this.getClass().getDeclaredField("mSuccessMsg");
            AnnotationField appAnnot = field.getAnnotation(AnnotationField.class);
            if (appAnnot != null) {
                Toast.makeText(this, "field:" + field.getName() + ",mSuccessMsg:" + appAnnot.descInfo(), Toast.LENGTH_SHORT).show();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //只有运行时注解才能获取到
        Annotation annotationClass = MainActivity.class.getAnnotation(AnnotationClass.class);
        if (annotationClass != null) {
            annotationClass.annotationType();
        }

        try {
            Method[] method = MainActivity.class.getDeclaredMethods();
            int i = method.length;
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
    }

    public void getMesssage() {

    }

}
