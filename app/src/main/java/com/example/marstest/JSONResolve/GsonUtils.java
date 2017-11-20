package com.example.marstest.JSONResolve;

import com.google.gson.Gson;

/**
 * Created by 钧童 on 2017/10/12.
 */

public class GsonUtils {
    public void parseUserFromJson(String gsonData){
        Gson gson = new Gson();
        //将json的数据转换为User对象
        User user = gson.fromJson(gsonData, User.class);
        System.out.println("user name------>" + user.getName());
        System.out.println("user age------>" + user.getAge());
    }
}
