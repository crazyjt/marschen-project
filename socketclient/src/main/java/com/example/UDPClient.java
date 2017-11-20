package com.example;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args){
        try {
            //创建DatagramSocket对象，并且设置端口
            DatagramSocket datagramSocket = new DatagramSocket(30001);
            //创建InetAdress对象，代表网络中一个地址（数据发送目的地址）
            InetAddress inetAddress = InetAddress.getByName("192.168.191.1");
            String str = "hello UDP";
            byte[] data = str.getBytes();
            //创建DatagramPacket包，放入数据
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, 30001);
            //调用DatagramSocket的send()方法发送数据包
            datagramSocket.send(datagramPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
