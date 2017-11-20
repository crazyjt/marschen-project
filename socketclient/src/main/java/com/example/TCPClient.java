package com.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by 钧童 on 2017/7/26.
 */

public class TCPClient {
    public static void main(String[] args){
        try{
            System.out.println("before connect");
            //创建一个Socket对象，指定目的网络地址和端口号
            Socket socket = new Socket("192.168.191.1", 30001);
            System.out.println("after connect");
            InputStream inputStream = new FileInputStream("D://Android/Project/MarsTest/workers.txt");
            //创建OutputStream对象，用于将数据用输出流的方式发送到服务器
            OutputStream outputStream = socket.getOutputStream();
            byte buffer [] = new byte[4 *1024];
            int temp = 0;
            while ((temp = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, temp);
            }
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
