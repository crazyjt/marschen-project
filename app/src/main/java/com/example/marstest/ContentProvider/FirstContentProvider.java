package com.example.marstest.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;


import java.util.HashMap;

/**
 * Created by 钧童 on 2017/7/19.
 */

public class FirstContentProvider extends ContentProvider {

    //定义UriMatcher,检查URI是否符合规则标准
    public static final UriMatcher uriMatcher;
    public static final int INCOMING_USER_COLLECTION = 1;
    public static final int INCOMING_USER_SINGLE = 2;
    private DatabaseHelperForCP dh;

    //定义Uri规则
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //访问users目录下一系列内容时对应的编号是INCOMING_USER_COLLECTION
        uriMatcher.addURI(ContentProviderMetaData.AUTHORIY, "/users", INCOMING_USER_COLLECTION);
        //访问users目录下某个内容时对应的编号是INCOMING_USER_SINGLE
        uriMatcher.addURI(ContentProviderMetaData.AUTHORIY, "/users/#", INCOMING_USER_SINGLE);

    }

    //给列起别名
    public static HashMap<String, String> userProjectionMap;
    static {
        userProjectionMap = new HashMap<String, String>();
        userProjectionMap.put(ContentProviderMetaData.UserTableMetaData._ID, ContentProviderMetaData.UserTableMetaData._ID);
        userProjectionMap.put(ContentProviderMetaData.UserTableMetaData.USER_NAME, ContentProviderMetaData.UserTableMetaData.USER_NAME);
    }

    @Override
    public boolean onCreate() {
        System.out.println("onCreate");
        dh = new DatabaseHelperForCP(getContext(), ContentProviderMetaData.DATABASE_NAME, ContentProviderMetaData.DATABASE_VERSION);
        return true;
    }

    /*
    * Uri是需要查询数据的Uri
    * projection是需要查询的列
    * selection是where字句的内容
    * selectionArgs是where自居占位符所对应的参数
    * sortOrder是排序方式*/
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        //SQLiteQueryBuilder对象相当于查询语句
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)){
            case INCOMING_USER_COLLECTION:
                qb.setTables(ContentProviderMetaData.UserTableMetaData.TABLE_NAME);
                qb.setProjectionMap(userProjectionMap);
                break;
            case INCOMING_USER_SINGLE:
                qb.setTables(ContentProviderMetaData.UserTableMetaData.TABLE_NAME);
                qb.setProjectionMap(userProjectionMap);
                //设置条件语句
                qb.appendWhere(ContentProviderMetaData.UserTableMetaData._ID + "=" + uri.getPathSegments().get(1));
                break;
        }
        String orderBy;
        //判断函数所传进来的参数sortOrder字符串是否为空或者长度为0
        if(TextUtils.isEmpty(sortOrder)){
            orderBy = ContentProviderMetaData.UserTableMetaData.DEFAULT_SORT_ORDER;
        }
        else {
            orderBy = sortOrder;
        }
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        System.out.println("query");
        return cursor;
    }

    //根据传入的URI，返回该URI对应的数据类型
    @Override
    public String getType(Uri uri) {
        System.out.println("getType");
        //match()方法匹配相应的uri，返回相应的类型
        switch (uriMatcher.match(uri)){
            case INCOMING_USER_COLLECTION:
                return ContentProviderMetaData.UserTableMetaData.CONTENT_TYPE;
            case INCOMING_USER_SINGLE:
                return ContentProviderMetaData.UserTableMetaData.CONTENT_TYPE_ITEM;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
    }

    //函数返回一个刚使用此函数所插入的数据
    //如content://com.example.marstest.ContentProvider.FirstContentProvider/users/新增id
    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        System.out.println("insert");
        SQLiteDatabase db = dh.getWritableDatabase();
        //insert成功则返回新数据的rowId,否则返回-1
        long rowId = db.insert(ContentProviderMetaData.UserTableMetaData.TABLE_NAME, null, values);
        if(rowId > 0){
            //将新生成的id追加到CONTENT_URI后面
            Uri insertedUserUri = ContentUris.withAppendedId(ContentProviderMetaData.UserTableMetaData.CONTENT_URI, rowId);
            //通知监听器，数据已经改变
            //ContentResolver对象可以对CcontentProvider进行操作
            getContext().getContentResolver().notifyChange(insertedUserUri, null);
            return insertedUserUri;
        }
        throw new SQLException("Failed to insert row into" + uri);
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri, ContentValues values,  String selection, String[] selectionArgs) {
        return 0;
    }
}
