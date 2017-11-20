package com.example.marstest;

import android.app.Activity;
import android.app.Service;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 钧童 on 2017/7/25.
 */

public class WifiActivity extends Activity {
    WifiManager wifiManager = null;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        Button start = (Button)findViewById(R.id.btnWifiStart);
        Button stop = (Button)findViewById(R.id.btnWifiStop);
        Button status = (Button)findViewById(R.id.btnWifiStatus);

        start.setOnClickListener(new StartListener());
        stop.setOnClickListener(new StopListener());
        status.setOnClickListener(new StatusListener());
    }

    class StartListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            wifiManager = (WifiManager)WifiActivity.this.getSystemService(Service.WIFI_SERVICE);
            //打开wifi网卡
            wifiManager.setWifiEnabled(true);
            //getWifiState()获取网卡状态
            System.out.println(wifiManager.getWifiState());
            String statusStr = statusJudge(wifiManager.getWifiState());
            Toast.makeText(WifiActivity.this, "当前wifi网卡状态" + statusStr, Toast.LENGTH_LONG).show();
        }
    }

    class StopListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            wifiManager = (WifiManager)WifiActivity.this.getSystemService(Service.WIFI_SERVICE);
            //关闭wifi网卡
            wifiManager.setWifiEnabled(false);
            System.out.println(wifiManager.getWifiState());
            String statusStr = statusJudge(wifiManager.getWifiState());
            Toast.makeText(WifiActivity.this, "当前wifi网卡状态" + statusStr, Toast.LENGTH_LONG).show();
        }
    }

    class StatusListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            wifiManager = (WifiManager)WifiActivity.this.getSystemService(Service.WIFI_SERVICE);
            String statusStr = statusJudge(wifiManager.getWifiState());
            Toast.makeText(WifiActivity.this, "当前wifi网卡状态" + statusStr, Toast.LENGTH_LONG).show();
        }
    }

    public String statusJudge(int state){
        String statusStr = null;
        switch (state){
            case 0:
                statusStr = "正在关闭!";
                break;
            case 1:
                statusStr = "已经关闭!";
                break;
            case 2:
                statusStr = "正在打开!";
                break;
            case 3:
                statusStr = "已经打开!";
                break;
            case 4:
                statusStr = "未知状态!";
                break;
        }
        return statusStr;
    }
}
