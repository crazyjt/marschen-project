package com.example.marstest.download;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 钧童 on 2017/7/18.
 */

public class FileUtils {
    private String SDPATH;

    public FileUtils() {
        //在构造函数里初始化SD卡路径
        SDPATH = Environment.getExternalStorageDirectory() + "/";
    }

    public String getSDPATH() {
        return SDPATH;
    }

    //在SD卡上创建目录
    public File createSDDir(String dirName){
        //创建File文件对象
        File dir = new File(SDPATH + dirName);
        //创建目录
        dir.mkdir();
        return dir;
    }

    //在SD卡上创建文件
    public File createSDFile (String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        //创建文件
        file.createNewFile();
        return file;
    }

    //判断文件是否存在
    public boolean isFileExist(String fileName){
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    //将一个InputStream中的数据写入到SD卡
    //path是目的目录，fileName是文件名，input是数据源的输入流
    public File writeToSDFromInput(String path, String fileName, InputStream input){
        File file = null;
        OutputStream outputStream = null;
        try{
            createSDDir(path);
            file = createSDFile(path + "/" + fileName);
            //创建OutputStream，并且数据会写入参数file的文件中
            outputStream = new FileOutputStream(file);
            //4K为单位，每4K写一次
            byte buffer[] = new byte[4 * 1024];
            //将InputStream输入流的数据写入buffer数组
            //在将buffer数组中的数据写入到OutputStream
            while ((input.read(buffer)) != -1){
                outputStream.write(buffer);
            }
            //清空缓存
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return file;
    }
}
