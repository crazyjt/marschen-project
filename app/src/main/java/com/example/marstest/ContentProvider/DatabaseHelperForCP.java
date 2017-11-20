package com.example.marstest.ContentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 钧童 on 2017/7/16.
 */
    //SQLiteOpenHelper是SQLite的助手类，提供两个功能：
    //1、getReadableDatabase(),getWritableDatabase()来创建并获得SQLiteDatabase对象。
    //2、提供onCreate()和onUpgrade()两个回调函数，允许在创建升级数据库进行操作。
public class DatabaseHelperForCP extends SQLiteOpenHelper {
    //context是指Activity对象，name是数据库名，version是数据库版本
    public DatabaseHelperForCP(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseHelperForCP(Context context, String name, int version){
        //调用四个参数的构造函数
        this(context,name,null,version);
    }
    public DatabaseHelperForCP(Context context, String name){
        //调用三个参数的构造函数，并且指定版本为1
        this(context,name,1);
    }

    //第一次创建数据库时调用onCreate()，即调用getReadableDatabase()或者getWritableDatabase()方法才调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Create a Database");
        //创建表，并且ID列为主键，而且自增长
        db.execSQL("create table " + ContentProviderMetaData.USERS_TABLE_NAME
                + "(" + ContentProviderMetaData.UserTableMetaData._ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ContentProviderMetaData.UserTableMetaData.USER_NAME
                + " varchar(20));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Update a Database");
    }


}
