package com.example.marstest.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.marstest.R;

/**
 * Created by 钧童 on 2017/7/30.
 */

public class ServiceActivity02 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service02);

        Button bind = (Button)findViewById(R.id.btnBindService);

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //声明Intent对象
                Intent intent = new Intent();
                intent.setClass(ServiceActivity02.this, ServiceTest02.class);
                //调用bindService进行Service绑定
                //BIND_AUTO_CREATE常量表示绑定service时自动生成service对象
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                System.out.println("in onClick");
            }
        });
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        //当Service与Activity绑定成功时调用
        //service参数是绑定时Service子类的onBind方法所返回的IBinder对象
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServiceTest02.FirstBinder firstBinder = (ServiceTest02.FirstBinder)service;
            String data = firstBinder.getData();
            System.out.println("data----->" + data);
        }

        //当Service与Activity解除绑定时调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
