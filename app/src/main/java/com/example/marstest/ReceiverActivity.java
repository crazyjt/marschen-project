package com.example.marstest;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.view.View;
import android.widget.Button;

/**
 * Created by 钧童 on 2017/7/23.
 */

public class ReceiverActivity extends Activity {

    //android定义的一个关于发送短信的Action常量字符串android.provider.Telephony.SMS_RECEIVED
    private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    SMSReceiver smsReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver);

        Button receiver = (Button)findViewById(R.id.btnReceiver);
        Button smsbind = (Button)findViewById(R.id.btnSMSReceiverBind);
        Button smsunbind = (Button)findViewById(R.id.btnSMSReceiverUnbind);
        Button smssend = (Button)findViewById(R.id.btnSendSMS);
        receiver.setOnClickListener(new ReceiverListener());
        smsbind.setOnClickListener(new BindListener());
        smsunbind.setOnClickListener(new UnbindListener());
        smssend.setOnClickListener(new SendListener());
    }

    class ReceiverListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            //设置Action属性
            //只有注册对此Action有相应回应的Receiver（即在AndroidManifest中的定义有action.EDIT的Receiver）才会做出回应
            intent.setAction(Intent.ACTION_EDIT);
            //调用发送广播方法
            ReceiverActivity.this.sendBroadcast(intent);
        }
    }

    class BindListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            System.out.println("BindListener");
            //初始化创建广播接收对象
            smsReceiver = new SMSReceiver();
            //定义广播过滤器对象
            IntentFilter intentFilter = new IntentFilter();
            //添加短信的广播Action到过滤器
            intentFilter.addAction(SMS_ACTION);
            System.out.println("action: " + intentFilter.getAction(0).toString());
            //注册广播监听器，第一个参数是广播接收对象，第二个参数是过滤器
            registerReceiver(smsReceiver, intentFilter);
        }
    }

    class UnbindListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            System.out.println("UnbindListener");
            //解除广播监听器
            ReceiverActivity.this.unregisterReceiver(smsReceiver);
        }
    }

    class SendListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("smsto://10010");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", "CXLL");
            startActivity(intent);
        }
    }
}
