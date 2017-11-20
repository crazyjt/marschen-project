package com.example.marstest.JSONResolve;

import android.util.JsonReader;

import java.io.StringReader;

/**
 * Created by 钧童 on 2017/10/12.
 */

public class JsonUtils {
    public void parseJson(String jsonData){
        try{
            //生成JsonReader
            JsonReader jsonReader = new JsonReader(new StringReader(jsonData));
            //开始解析数组
            jsonReader.beginArray();
            while (jsonReader.hasNext()){
                //开始解析数组内的对象
                jsonReader.beginObject();
                while (jsonReader.hasNext()){
                    //获取键
                    String tagName = jsonReader.nextName();
                    if(tagName.equals("name"))
                        System.out.println("name----->" + jsonReader.nextString());
                    else if(tagName.equals("age"))
                        System.out.println("age----->" + jsonReader.nextInt());
                }
                jsonReader.endObject();
            }
            jsonReader.endArray();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
