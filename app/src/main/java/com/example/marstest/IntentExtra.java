package com.example.marstest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

public class IntentExtra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_extra);
        Button intextExtra = (Button)findViewById(R.id.btnIntentExtra);
        Button sendTo = (Button)findViewById(R.id.btnSendTo);

        intextExtra.setOnClickListener(new MyOnClickListener());
        sendTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Uri让本应用程序启动发短信的应用程序
                Uri uri = Uri.parse("smsto://13178848059");
                //新建Intent实例需要用Intent.ACTION_SENDTO和URI对象作为参数
                Intent intent1 = new Intent(Intent.ACTION_SENDTO,uri);
                intent1.putExtra("sms_body", "The SMS text");
                startActivity(intent1);
            }
        });

    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            //用putExtra()方法向intent传入内容，内容为key-value键值对数据
            intent.putExtra("key","value");
            intent.setClass(IntentExtra.this,IntentExtra01.class);
            startActivity(intent);
        }
    }
}
