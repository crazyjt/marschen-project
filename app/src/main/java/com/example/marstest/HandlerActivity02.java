package com.example.marstest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/**
 * Created by 钧童 on 2017/7/16.
 */

public class HandlerActivity02 extends Activity {
    protected void onCreate(Bundle savedInstanceStated){
        super.onCreate(savedInstanceStated);
        setContentView(R.layout.activity_handler02);

        //打印当前线程id
        System.out.println("Activity's Thread :" + Thread.currentThread().getId() + "   " + Thread.currentThread().getName());
        //生成HandlerThread对象，实现使用looper来处理消息队列的功能，这个类由Android应用程序框架提供
        HandlerThread handlerThread = new HandlerThread("handler_thread");
        //在使用getLooper方法之前必须先启动该线程
        handlerThread.start();

        //用HandlerThread对象的getLooper()方法来获得lopper对象
        //利用该looper新建一个自定义的Handler类的对象
        MyHandler myHandler = new MyHandler(handlerThread.getLooper());
        //创建Message对象，将Handler的message对象赋给它
        Message msg = myHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putInt("age", 22);
        bundle.putString("name", "DaDi");
        //将bundle设置到msg对象中
        msg.setData(bundle);
        //sencToTarget方法是将msg发给创建它的Handler对象，也就是调用myHandler的Message方法
        msg.sendToTarget();
    }

    class MyHandler extends Handler{
        public MyHandler(){

        }
        //让Handler使用该looper的线程来传递数据（即不用与Activity同一个线程）
        public MyHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            //获取传来的bundle数据
            Bundle bundle = msg.getData();
            String name = bundle.getString("name");
            int age = bundle.getInt("age");
            System.out.println("name: " + name + "  age: " + age);
            super.handleMessage(msg);
            System.out.println("Handler's Thread :" + Thread.currentThread().getId() + "   " + Thread.currentThread().getName());
        }
    }
}
