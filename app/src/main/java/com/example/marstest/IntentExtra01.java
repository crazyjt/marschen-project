package com.example.marstest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by 钧童 on 2017/7/14.
 */

public class IntentExtra01 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_extra01);

        TextView intentExtra01 = (TextView)findViewById(R.id.tvIntentExtra01);
        //用getIntent()方法获得其他Activity传来的intent对象
        Intent intent = getIntent();
        //调用getStringExtra方法，将键作为参数，得到对应的value值
        String value = intent.getStringExtra("key");
        intentExtra01.setText(value);
    }
}
