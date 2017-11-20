package com.example.marstest.UserLocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.marstest.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;


/**
 * Created by 钧童 on 2017/10/15.
 */

public class UserLocationTest extends Activity {
    private Button userlocationButton;
    private Button bestProviderButton;
    private Button geocodingButton;
    private Button reverseGeocodingButton;
    private Button byHttpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlocation);

        userlocationButton = (Button) findViewById(R.id.btnUserLocation);
        bestProviderButton = (Button)findViewById(R.id.btnBestProvider);
        geocodingButton = (Button)findViewById(R.id.btnGeocoding);
        byHttpButton = (Button)findViewById(R.id.btnUserLocationByHttp);
        reverseGeocodingButton = (Button)findViewById(R.id.btnReverseGeocoding);
        userlocationButton.setOnClickListener(new UserLocationButtonListener());
        bestProviderButton.setOnClickListener(new BestProviderButtonListener());
        geocodingButton.setOnClickListener(new GeocodingButtonListener());
        reverseGeocodingButton.setOnClickListener(new ReverseButtonListener());
        byHttpButton.setOnClickListener(new ByHttpButtonListener());
    }

    private class UserLocationButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //getSystemService(Context.LOCATION_SERVICE)方法获的LocationManager对象
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            //获得所有的Location Provider
            List<String> providers = locationManager.getAllProviders();
            for(Iterator iterator = providers.iterator(); iterator.hasNext(); ){
                System.out.println("provider:   " + iterator.next().toString());
            }

            if(UserLocationTest.this.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED)
                //调用requestLocationUpdates方法执行定位
                //LocationManager.GPS_PROVIDER为当前使用的Location Provider
                //第二个参数表示相隔定位更新时间，单位ms
                //第三个参数表示相隔定位更新距离，单位m
                //new LocationTestListener()为自定义的继承与LocationListener的监听类
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 500, new LocationTestListener());
        }
    }

    private class BestProviderButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //getSystemService(Context.LOCATION_SERVICE)方法获的LocationManager对象
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            //创建Criteria对象用于设置条件
            Criteria criteria = new Criteria();
            //设置精度，参数Criteria.ACCURACY_FINE表示高精度
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            //设置是否需要海拔信息
            criteria.setAltitudeRequired(false);
            //设置方向经度，参数Criteria.ACCURACY_HIGH表示方向精度高
            criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
            //设置是否需要方向信息
            criteria.setBearingRequired(true);
            //设置获取定位时是否产生费用
            criteria.setCostAllowed(false);
            //设置水平精度
            criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
            //设置垂直方向精度
            criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
            //设置电量消耗级别
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            //参数1是Criteria对象，参数2表示是否在已经开启的provider中寻找最合适的provider
            String provider = locationManager.getBestProvider(criteria, false);
            System.out.println("best provider:  " + provider);
        }
    }

    private class GeocodingButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            new GeocodingTask().execute();
        }
    }

    private class ReverseButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            new ReverseTask().execute();
        }
    }

    private class ByHttpButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DownloadThread downloadThread = new DownloadThread();
            new Thread(downloadThread, "DownloadThread").start();
        }
    }

    //继承于AsyncTask进行异步操作
    private class GeocodingTask extends AsyncTask<Integer, Integer, Integer>{
        @Override
        protected Integer doInBackground(Integer... params) {
            try{
                //创建Geocoding对象
                Geocoder geocoder = new Geocoder(UserLocationTest.this);
                //geocoder.getFromLocationName()方法返回指定具体地址的经纬度
                //geocoder.getFromLocation()方法返回指定经纬度的具体地址
                //第二个参数表示最大返回结果个数
                List<Address> addresses = geocoder.getFromLocationName("SFO", 1);
                System.out.println("同名地址个数： " + addresses.size());
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    private class ReverseTask extends AsyncTask<Integer, Integer, Integer>{
        @Override
        protected Integer doInBackground(Integer... params) {
            try{
                Geocoder geocoder = new Geocoder(UserLocationTest.this);
                //geocoder.getFromLocation方法通过经纬度获得具体地址
                List<Address> addresses = geocoder.getFromLocation(40.123, 30.321, 1);
                for(Iterator iterator = addresses.iterator(); iterator.hasNext(); ){
                    Address address = (Address) iterator.next();
                    System.out.println("同经纬度的地址：    " + address);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    private class LocationTestListener implements LocationListener{
        //当设备位置发生改变时调用
        @Override
        public void onLocationChanged(Location location) {
            String address = "当前位置：" + location.getLatitude() + "," + location.getLongitude();
            Toast.makeText(UserLocationTest.this, address, Toast.LENGTH_LONG);
            System.out.println("当前经纬度为：(" + location.getLongitude() + "," + location.getLatitude() + ")");
        }
        //当数据提供者状态发生改变时调用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        //当数据提供者可以使用时调用
        @Override
        public void onProviderEnabled(String provider) {

        }
        //当数据提供者不能再使用时调用
        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    private class DownloadThread implements Runnable{
        @Override
        public void run() {
            String urlStr = "https://maps.googleapis.com/maps/api/geocode/json?address=BeiJing&sensor=false";
            String result = "";
            try {
                //通过访问网址，返回字符串内容
                URL url = new URL(urlStr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line = null;
                while ( (line = bufferedReader.readLine()) != null){
                    System.out.println("line:   " + result);
                    result = result + line;
                }
                System.out.println("result: " + result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //json数据解析
            Gson gson = new Gson();
            com.example.marstest.UserLocation.Location location = gson.fromJson(result, com.example.marstest.UserLocation.Location.class);
            System.out.println("UserLocation By Http:   " + location.toString());
        }
    }
}
