package com.example.marstest.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.marstest.R;

/**
 * Created by 钧童 on 2017/7/27.
 */

public class ServiceActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Button start = (Button)findViewById(R.id.btnStartService);
        Button stop = (Button)findViewById(R.id.btnStopService);

        start.setOnClickListener(new StartListener());
        stop.setOnClickListener(new StopListener());
    }

    class StartListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(ServiceActivity.this, ServiceTest.class);
            //启动Service
            startService(intent);
        }
    }

    class StopListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(ServiceActivity.this, ServiceTest.class);
            //停止Service
            stopService(intent);
        }
    }
}
