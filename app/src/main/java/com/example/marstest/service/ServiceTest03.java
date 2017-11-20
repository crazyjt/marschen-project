package com.example.marstest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by 钧童 on 2017/7/30.
 */

public class ServiceTest03 extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder{
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            System.out.println("code----->" + code);
            //data是Activity传来的数据
            String str = data.readString();
            System.out.println("data string: " + str);
            //reply用于写入数据之后发送给Activity
            reply.writeString("drom Service : reply");
            return super.onTransact(code, data, reply, flags);
        }
    }
}
