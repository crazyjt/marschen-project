package com.example.marstest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by 钧童 on 2017/9/24.
 */

public class TweenedAnimations extends Activity {

    private ImageView imageView = null;
    private Button alpha = null;
    private Button rotate = null;
    private Button scale = null;
    private Button translate = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweened_animations);

        imageView = (ImageView)findViewById(R.id.ivTweendedAnimations);
        alpha = (Button)findViewById(R.id.btnAlpha);
        rotate = (Button)findViewById(R.id.btnRotate);
        scale = (Button)findViewById(R.id.btnScale);
        translate = (Button)findViewById(R.id.btnTranslate);

        alpha.setOnClickListener(new AlphaButtonListener());
        rotate.setOnClickListener(new RotateButtonListener());
        scale.setOnClickListener(new ScaleButtonListener());
        translate.setOnClickListener(new TranslateButtonListener());
    }

    //渐变动画
    private class AlphaButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //创建AnimationSet对象，用于存放一系列Animation
            AnimationSet animationSet = new AnimationSet(true);
            //创建AlphaAnimation渐变动画对象
            //参数1：完全不透明
            //参数0：完全透明
            AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
            //设置动画持续时间，单位ms
            alphaAnimation.setDuration(1000);
            //将alphaAnimation加入AnimationSet序列
            animationSet.addAnimation(alphaAnimation);
            //startAnimation方法设置图片开始执行动画
            imageView.startAnimation(animationSet);
            animationSet.setInterpolator(new AccelerateInterpolator());
            animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animationSet.setInterpolator(new CycleInterpolator(45));
            animationSet.setInterpolator(new DecelerateInterpolator());
            animationSet.setInterpolator(new LinearInterpolator());
        }
    }

    //旋转动画
    private class RotateButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1：旋转起始角度
            //参数2：旋转终止角度
            //参数3：Animation.RELATIVE_TO_PARENT（父控件宽度作为X轴）、Animation.RELATIVE_TO_SELF（控件本身宽度作为X轴）、Animation.ABSOLUTE（绝对坐标）
            //参数4：表示X轴根据比例得到的坐标点的X值作为旋转圆心的X值
            //参数5：Animation.RELATIVE_TO_PARENT（父控件高度作为Y轴）、Animation.RELATIVE_TO_SELF（控件本身高度作为Y轴）、Animation.ABSOLUTE（绝对坐标）
            //参数6：表示Y轴根据比例得到的坐标点的Y值作为旋转圆心的Y值
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                                            Animation.RELATIVE_TO_PARENT, 0.5f,
                                            Animation.RELATIVE_TO_PARENT, 0.5f);
            rotateAnimation.setDuration(3000);
            animationSet.addAnimation(rotateAnimation);
            imageView.startAnimation(animationSet);
        }
    }

    //缩放动画
    private class ScaleButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1：宽度缩放起始值
            //参数2：宽度缩放终止值
            //参数3：高度缩放起始值
            //参数4：高度缩放终止值
            //参数5：缩放中心点的X轴
            //参数6：表示X轴根据比例得到的坐标点的X值作为缩放中心点的坐标X值
            //参数7：缩放中心点的Y轴
            //参数8：表示Y轴根据比例得到的坐标点的Y值作为缩放中心点的坐标Y值
            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.1f, 1, 0.1f,
                                                            Animation.RELATIVE_TO_SELF, 0.5f,
                                                            Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(3000);
            animationSet.addAnimation(scaleAnimation);
            imageView.startAnimation(animationSet);
        }
    }

    //移动动画
    private class TranslateButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1、2：指定X轴起始位置
            //参数3、4：指定X轴终止位置
            //参数5、6：指定Y轴起始位置
            //参数7、8：指定Y轴终止位置
            //默认X轴正方向为正数，Y轴负方向为整数
            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f,
                                                                        Animation.RELATIVE_TO_PARENT, 0.3f,
                                                                        Animation.RELATIVE_TO_PARENT, 0f,
                                                                        Animation.RELATIVE_TO_PARENT, -0.3f);
            translateAnimation.setDuration(3000);
            animationSet.addAnimation(translateAnimation);
            imageView.startAnimation(animationSet);

        }
    }
}
