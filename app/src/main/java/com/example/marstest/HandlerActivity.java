package com.example.marstest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by 钧童 on 2017/7/16.
 */

public class HandlerActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        Button start = (Button)findViewById(R.id.btnStart);
        Button end = (Button)findViewById(R.id.btnEnd);

        start.setOnClickListener(new startOnClick());
        end.setOnClickListener(new endOnClick());
    }

    //定义handler用于传递消息
    Handler handler = new Handler();

    //定义子线程
    Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            System.out.println("------updateThread------");
            //将子线程加入消息队列中，每隔3000ms加入一次
            handler.postDelayed(updateThread,3000);
        }
    };

    class startOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //第一次将子线程加入handler中
            handler.post(updateThread);
        }
    }

    class endOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            handler.removeCallbacks(updateThread);
        }
    }
}
