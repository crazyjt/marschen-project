package com.example.marstest.ContentProvider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 此类用于存放所需要的静态常量
 * Created by 钧童 on 2017/7/19.
 */
public class ContentProviderMetaData {
    public static final String AUTHORIY = "com.example.marstest.ContentProvider.FirstContentProvider";
    //数据库名称
    public static final String DATABASE_NAME = "FirstProvider.db";
    //数据库版本
    public static final int DATABASE_VERSION = 1;
    //表名
    public static final String USERS_TABLE_NAME = "users";

    //BaseColumns接口已经定义了_ID列
    public static final class UserTableMetaData implements BaseColumns{
        //表名
        public static final String TABLE_NAME = "users";
        //访问该ContentProvider的Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORIY + "/users");
        //该ContentProvider所返回的数据类型定义，用于访问整张表的数据
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.firstprovider.user";
        //该ContentProvider所返回的数据类型定义，用于访问表中的某条数据
        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.firstprovider.user";
        //列名
        public static final String USER_NAME = "name";
        //默认排序方式
        public static final String DEFAULT_SORT_ORDER = "_id desc";

    }
}
