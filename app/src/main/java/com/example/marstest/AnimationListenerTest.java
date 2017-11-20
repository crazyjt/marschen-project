package com.example.marstest;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by 钧童 on 2017/9/28.
 */

public class AnimationListenerTest extends Activity {
    private Button add = null;
    private Button remove = null;
    private ImageView iv = null;
    //ViewGroup表示控件的组
    private ViewGroup viewGroup = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_listener);

        add = (Button)findViewById(R.id.btnAdd);
        remove = (Button)findViewById(R.id.btnRemove);
        iv = (ImageView)findViewById(R.id.ivAnimationListener);
        //获取布局中所有控件到ViewGroup中
        viewGroup = (ViewGroup)findViewById(R.id.animationListenerLayout);

        add.setOnClickListener(new AddButtonListener());
        remove.setOnClickListener(new RemoveButtonListener());

    }

    private class AddButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(1000);
            alphaAnimation.setStartOffset(500);
            //新生成ImageView控件
            ImageView imageView = new ImageView(AnimationListenerTest.this);
            imageView.setImageResource(R.drawable.one);
            //将新建图片控件加入布局的ViewGroup中
            viewGroup.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.startAnimation(alphaAnimation);
        }
    }

    private class RemoveButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            System.out.println("RemoveButtonListener");
            AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
            animation.setDuration(1000);
            animation.setStartOffset(500);
            //为动画设置监听器
            animation.setAnimationListener(new RemoveAnimationListener());
            iv.startAnimation(animation);
        }
    }
    //自定义监听器实现AnimationListener接口，并且复写各个方法
    private class RemoveAnimationListener implements Animation.AnimationListener{
        //动画开始时调用
        @Override
        public void onAnimationStart(Animation animation) {
            System.out.println("onAnimationStart");
        }
        //动画结束时调用
        @Override
        public void onAnimationEnd(Animation animation) {
            System.out.println("onAnimationEnd");
            //从当前的布局中去掉ImageView控件
            viewGroup.removeView(iv);
        }
        //动画重复时调用
        @Override
        public void onAnimationRepeat(Animation animation) {
            System.out.println("onAnimationRepeat");
        }
    }
}
