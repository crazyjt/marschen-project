package com.example.marstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by 钧童 on 2017/7/24.
 */

public class SMSReceiver extends BroadcastReceiver {

    public SMSReceiver(){
        System.out.println("SMSReciver Construction");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("SMSReceiver_onReceive");

        //接收intent传过来的数据
        Bundle bundle = intent.getExtras();
        //在Bundle对象中有一个属性名为"pdus"，该属性值为object数组
        Object[] objects = (Object[]) bundle.get("pdus");
        //创建一个SmsMessage类型的数组，用于将object中的数据转换为message数据
        SmsMessage[] smsMessage = new SmsMessage[objects.length];
        StringBuffer stringBuffer = null;
        for(int i = 0; i < objects.length; i++){
            //使用Object数组当中的对象创建SmsMessage对象
            smsMessage[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
            //使用SmsMessage对象的getDisplayMessageBody()方法获得消息内容
            System.out.println(smsMessage[i].getDisplayMessageBody());
            stringBuffer.append(smsMessage[i].getDisplayMessageBody().toString());
        }
        Toast.makeText(context, stringBuffer.toString(), Toast.LENGTH_LONG).show();
    }
}
