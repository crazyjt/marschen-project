package com.example.marstest.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.marstest.R;

/**
 * Created by 钧童 on 2017/7/31.
 */

public class ServiceActivity03 extends Activity{

    Binder binder = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service03);

        Button bind = (Button)findViewById(R.id.btnServiceBind03);
        Button send = (Button)findViewById(R.id.btnServiceSend03);

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ServiceActivity03.this, ServiceTest03.class);
                //绑定Service
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //生成两个Parcel对象用于transact方法的参数
                //data用于向Service发送数据
                //reply是Sevice向Activity发送的数据
                Parcel data = Parcel.obtain();
                Parcel replay = Parcel.obtain();
                //用writeXXX()方法向parcel中写入数据
                data.writeString("from activity : data");
                try {
                    //调用service子类中，继承于Binder类的子类所复写的onTransact方法
                    binder.transact(0, data, replay, 0);
                    //用readXXX()方法读取parcel中的数据
                    String str = replay.readString();
                    System.out.println("reply string: " + str);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (Binder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
