package com.example.marstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 钧童 on 2017/7/23.
 */

public class TestReceiver extends BroadcastReceiver {

    public TestReceiver(){
        System.out.println("TestReceiver");
    }

    //当每次使用此方法之后，该TestReceiver对象就会作废
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive");
        Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show();
    }
}
