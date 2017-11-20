package com.example.marstest.socket;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.marstest.R;

import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 钧童 on 2017/7/25.
 */

public class SocketActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        Button launch = (Button)findViewById(R.id.btnSocket);
        launch.setOnClickListener(new launchListener());
    }

    class launchListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            new UDPServerThread().start();
//            new TCPServerThread().start();
        }
    }

    //TCP服务端
    class TCPServerThread extends Thread{
        @Override
        public void run() {
            //创建ServerSocket对象
            ServerSocket serverSocket = null;
            try{
                System.out.println("TCP Thread");
                //设置ServerSocket监听30000端口
                serverSocket = new ServerSocket(30001);
                //调用ServerSocket的accept()方法创建Socket对象，接受客户端发送的请求
                //accept()方法会阻塞，直到客户端发送请求再往下执行
                Socket socket = serverSocket.accept();
                System.out.println(socket.isConnected());
                //从Socket中得到InputStream对象，即客户端传来的数据
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[4 * 1024];
                int temp = 0;
                while ((temp = inputStream.read(bytes)) != -1){
                    System.out.println(new String(bytes, 0, temp));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //UDP服务端
    class UDPServerThread extends Thread{
        @Override
        public void run() {
            try{
                System.out.println("UDP Thread");
                //创建一个DatagramSocket对象（相当于TCP的ServerSocket），并指定监听端口号30000
                DatagramSocket socket = new DatagramSocket(30001);
                byte[] datas = new byte[1024];
                //创建一个空的DatagramPacket对象，用于接收客户端发来的数据
                DatagramPacket packet = new DatagramPacket(datas, datas.length);
                //使用receive方法接收客户端发送的数据
                //receive方法会阻塞，直到客户端发送请求再往下执行
                System.out.println("before receive");
                socket.receive(packet);
                System.out.println(socket.isConnected());
                //用packet中的数据构造字符串，并且指定起始位置和数据长度
                String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
                System.out.println("UDP result: " + result);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
