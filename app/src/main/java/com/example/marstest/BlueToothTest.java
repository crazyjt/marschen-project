package com.example.marstest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by 钧童 on 2017/10/13.
 */

public class BlueToothTest extends Activity {
    private Button bluetooth;
    private Button discoverButton;
    private Button scanButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_test);

        bluetooth = (Button)findViewById(R.id.btnBlueTooth);
        discoverButton = (Button)findViewById(R.id.btnBlueToothDiscover);
        scanButton = (Button)findViewById(R.id.btnBlueToothScan);
        bluetooth.setOnClickListener(new BlueToothListener());
        discoverButton.setOnClickListener(new DiscoverButtonListener());
        scanButton.setOnClickListener(new ScanButtonListener());

        //动态注册广播，用于接收扫描到蓝牙后系统所发出的广播
        //创建过滤器，参数设置为BluetoothDevice.ACTION_FOUND
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        ScanReceiver scanReceiver = new ScanReceiver();
        //注册广播
        BlueToothTest.this.registerReceiver(scanReceiver, intentFilter);

    }

    //创建继承于BroadcastReceiver的类进行广播接收
    class ScanReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("receivering");
            //获取广播中的远程蓝牙适配器，键值对的键为BluetoothDevice.EXTRA_DEVICE
            BluetoothDevice receiverDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            System.out.println("可配对蓝牙地址：" + receiverDevice.getAddress());
        }
    }

    class BlueToothListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //得到BluetoothAdapter对象，代表本地蓝牙适配器
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            //判断BluetoothAdapter对象是否为空，为空则表示本机没有蓝牙设备
            if(bluetoothAdapter != null){
                System.out.println("本机拥有蓝牙设备");
                //isEnabled方法判断当前蓝牙是否可用
                if(!bluetoothAdapter.isEnabled()){
                    //BluetoothAdapter.ACTION_REQUEST_ENABLE参数表示开启蓝牙设备的Action
                    //如果蓝牙没有开启会弹出蓝牙权限请求
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(intent);
                }
                //getBondedDevices()方法得到所有已经配对的蓝牙适配器对象
                //BluetoothDevice代表远程设备的蓝牙适配器
                Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
                if(devices.size() > 0){
                    for(Iterator iterator = devices.iterator(); iterator.hasNext(); ){
                        BluetoothDevice device = (BluetoothDevice) iterator.next();
                        //getAddress()方法返回蓝牙地址
                        System.out.println(device.getAddress());
                    }
                }
            }
            else
                System.out.println("本机没有蓝牙设备");
        }
    }

    class DiscoverButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //创建关于蓝牙可扫描设置的Intent
            Intent discoverIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            //设置蓝牙可扫描状态持续时间200s
            discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 200);
            startActivity(discoverIntent);
        }
    }

    class ScanButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //获得本地蓝牙适配器
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            //开始扫描
            //每次扫描到蓝牙就发送一个广播
            bluetoothAdapter.startDiscovery();
            System.out.println("scaning");
        }
    }
}
