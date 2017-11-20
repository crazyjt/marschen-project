package com.example.marstest.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 钧童 on 2017/7/18.
 */

public class HttpDownloader {
    private URL url = null;
    /*
    * 根据url下载文件，该文件内容应是文本，函数返回值是文件的内容
    * 1、创建一个URL对象
    * 2、通过URL的openConnection()方法创建HttpURLConnection对象
    * 3、得到InputStream
    * 4、从InputStream中读取数据
    */
    public String downloadTxt(String urlStr){
        //stringBuffer用于存储文件中读取到的字符串
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            //用IO流读取数据
            //getInputStream()获得字节流
            //InputStreamReader()将字节流转换为字符流
            //BufferedReader()转换为BuffereReader可以逐行读取数据
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((line = bufferedReader.readLine()) != null){
                //将数据读入字符串流里
                stringBuffer.append(line);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try{
                bufferedReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
    //返回值为-1表示下载出错
    //返回值为0表示下载成功
    //返回值为1表示文件已经存在
    //参数urlStr表示文件链接，path是目标SD卡的目录，fileName文件名
    public int downloadMP3(String urlStr, String path, String fileName){
        InputStream inputStream = null;
        try{
            FileUtils fileUtils = new FileUtils();
            //判断文件是否已经存在
            if(fileUtils.isFileExist(path + fileName))
                return  1;
            else {
                try{
                    url = new URL(urlStr);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    inputStream = connection.getInputStream();
                    File resultFile = fileUtils.writeToSDFromInput(path, fileName, inputStream);
                    if(resultFile == null)
                        return -1;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            try{
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }
}
