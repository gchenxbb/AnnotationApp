package com.annotations.app;

import android.annotation.SuppressLint;
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

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.tv_bind1)
    TextView mTvBind1;
    @BindView(R.id.tv_bind2)
    TextView mTvBind2;
    @BindView(R.id.tv_bind3)
    TextView mTvBind3;
    @BindView(R.id.tv_bind4)
    TextView mTvBind4;
    @BindView(R.id.tv_bind5)
    TextView mTvBind5;

    @BindString(R.string.str_value1)
    String mStrValue1;
    @BindString(R.string.str_value2)
    String mStrValue2;
    @BindString(R.string.str_value3)
    String mStrValue3;
    @BindString(R.string.str_value4)
    String mStrValue4;
    @BindString(R.string.str_value5)
    String mStrValue5;

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

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.tv_bind1, R.id.tv_bind2, R.id.tv_bind3, R.id.tv_bind4, R.id.tv_bind5})
    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bind1:
                Toast.makeText(this, "bind1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_bind2:
                Toast.makeText(this, "bind2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_bind3:
                Toast.makeText(this, "bind3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_bind4:
                Toast.makeText(this, "bind4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_bind5:
                Toast.makeText(this, "bind5", Toast.LENGTH_SHORT).show();
                break;
        }
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
