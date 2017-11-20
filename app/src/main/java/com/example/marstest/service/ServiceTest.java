package com.example.marstest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by 钧童 on 2017/7/27.
 */

public class ServiceTest extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("ServiceTest onBind");
        return null;
    }

    //初次创建Service对象时调用
    @Override
    public void onCreate() {
        System.out.println("ServiceTest onCreate");
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("ServiceTest onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("ServiceTest onDestroy");
        super.onDestroy();
    }
}
