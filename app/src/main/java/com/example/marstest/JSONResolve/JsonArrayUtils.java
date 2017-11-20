package com.example.marstest.JSONResolve;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by 钧童 on 2017/10/12.
 */

public class JsonArrayUtils {
    public void parseUserFromJsonArray(String jsonArray){
        //创建Type对象
        Type listType = new TypeToken<LinkedList<User>>(){}.getType();
        Gson gson = new Gson();
        //将json数据转换为listType中的类型
        LinkedList<User> users = gson.fromJson(jsonArray, listType);
        for(Iterator iterator = users.iterator(); iterator.hasNext(); ){
            User user = (User) iterator.next();
            System.out.println("user name------>" + user.getName());
            System.out.println("user age------>" + user.getAge());
        }
    }


}
