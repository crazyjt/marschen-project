package com.example.marstest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by 钧童 on 2017/7/30.
 */

public class ServiceTest02 extends Service {

    //当其他应用程序组件（Activity）绑定至当前的Service对象时，调用该方法
    //IBinder是一个接口，Binder是实现IBinder接口的一个类
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("in onBind");
        IBinder iBinder = new FirstBinder();
        return iBinder;
    }

    //编写一个继承于Binder的内部类
    class FirstBinder extends Binder{
        public  String getData(){
            System.out.println("in getData");
            return "test data";
        }
    }
}
