package com.example.marstest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

/**
 * Created by 钧童 on 2017/9/24.
 */

public class TweenedAnimationForXML extends Activity {
    private ImageView imageView= null;
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

    private class AlphaButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //AnimationUtils装载动画设置文件
            Animation animation = AnimationUtils.loadAnimation(TweenedAnimationForXML.this, R.anim.alpha);
            imageView.startAnimation(animation);
        }
    }

    private class RotateButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(TweenedAnimationForXML.this, R.anim.rotate);
            imageView.startAnimation(animation);
        }
    }

    private class ScaleButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(TweenedAnimationForXML.this, R.anim.scale);
            imageView.startAnimation(animation);
        }
    }

    private class TranslateButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(TweenedAnimationForXML.this, R.anim.translate);
            imageView.startAnimation(animation);
        }
    }
}
