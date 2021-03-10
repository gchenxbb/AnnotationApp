package com.annotations.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.annotations.generated.GeneratedClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.annotations.lib.*;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 注解类
 */
@AnnotationClass(authorName = "chen", createTime = "2019年")
public class MainActivity extends Activity {
    public static final String TAG = "MainActivityAnnotation";

    //注解变量
    @AnnotationField(descInfo = "成功信息")
    String mSuccessMsg;

    @MyBindView(R.id.tv_message)
    TextView mTextView;


    @BindView(R.id.tv_bind)
    TextView mTvBind;

    StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mTextView = findViewById(R.id.tv_message);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.delete(0, sb.length());
                printRunTimeAnnotation();

                mTextView.setText(sb.toString());

                GeneratedClass generatedClass = new GeneratedClass();
                String info = generatedClass.getMessage();
                Toast.makeText(MainActivity.this, "info:" + info, Toast.LENGTH_SHORT).show();

            }
        });


    }

    /**
     * 运行时注解打印，包括方法注解、类注解和变量注解
     * 只有运行时注解才能获取到
     */
    void printRunTimeAnnotation() {
        try {
            Method method = MainActivity.class.getDeclaredMethod("initView", Context.class);
            AnnotationMethod appAnnot = method.getAnnotation(AnnotationMethod.class);
            if (appAnnot != null) {
                Log.d(TAG, "method:" + method.getName() + ",name:" + appAnnot.name());
                sb.append("method:" + method.getName() + ",name:" + appAnnot.name() + "\n");
            }

            Field field = this.getClass().getDeclaredField("mSuccessMsg");
            AnnotationField annotationField = field.getAnnotation(AnnotationField.class);
            if (appAnnot != null) {
                Log.d(TAG, "field:" + field.getName() + ",mSuccessMsg:" + annotationField.descInfo());
                sb.append("field:" + field.getName() + ",mSuccessMsg:" + annotationField.descInfo() + "\n");
            }
        } catch (NoSuchMethodException e) {
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        AnnotationClass annotationClass = this.getClass().getAnnotation(AnnotationClass.class);
        if (annotationClass != null) {
            String createTime = annotationClass.createTime();
            Log.d(TAG, "createTime:" + createTime + ",author:" + annotationClass.authorName());
            sb.append("createTime:" + annotationClass.createTime() + ",author:" + annotationClass.authorName() + "\n");
        }

    }

    /**
     * 注解方法
     *
     * @param context
     */
    @AnnotationMethod(name = "我的 initView() 方法")
    private void initView(Context context) {
    }

}
