package com.example.marstest;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by 钧童 on 2017/9/25.
 */

public class FrameToFrameAnimation extends Activity {
    private ImageView imageView = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_to_frame_animation);

        imageView = (ImageView)findViewById(R.id.ivFrameToFrame);
        //用setBackgroundResource设置图片资源
        //参数为drawable文件中的图片资源配置的xml文件
        imageView.setBackgroundResource(R.drawable.anim_nv);
        //imageView.getBackground()方法创建AnimationDrawable对象
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        //调用AminationDrawable对象的start()方法启动动画
        animationDrawable.start();
    }
}
