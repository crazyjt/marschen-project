package com.example.marstest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by 钧童 on 2017/7/16.
 */

public class SQLiteActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        Button createDatabase = (Button)findViewById(R.id.btnCreatedb);
        Button updateDatabase = (Button)findViewById(R.id.btnUpdatedb);
        Button insert = (Button)findViewById(R.id.btnInstert);
        Button update = (Button)findViewById(R.id.btnUpdate);
        Button select = (Button)findViewById(R.id.btnSelect);

        createDatabase.setOnClickListener(new createdbListener());
        updateDatabase.setOnClickListener(new updatedbListener());
        insert.setOnClickListener(new insertListener());
        update.setOnClickListener(new updateListener());
        select.setOnClickListener(new selectListener());
    }

    class createdbListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //用SQLiteDatabase助手类DatabaseHelper来创建数据库助手类对象
            DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"Database_test1");
            //调用getReadableDatabase()方法创建并取得数据库对象，会自动调用onCreate()函数
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

    class updatedbListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //更新数据库操作，需要创建DatabaseHelper对象，并且指定版本号，必须比之前的版本号大
            DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this, "Database_test1", 2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }


    class insertListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //生成DatabaseHelper对象获取数据库
            DatabaseHelper databaseHelper = new DatabaseHelper(SQLiteActivity.this, "Database_test1", 2);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            //生成ContentValues对象用于暂时存放数据
            ContentValues contentValues = new ContentValues();
            //数据是键值对，并且键为数据库中表的字段名
            contentValues.put("id", 1);
            contentValues.put("name", "DaDi");

            //进行插入操作
            //第一个参数是表名，最后一个参数是插入数据的ContentValues对象
            db.insert("user", null, contentValues);

        }
    }

    class updateListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DatabaseHelper databaseHelper = new DatabaseHelper(SQLiteActivity.this, "Database_test1", 2);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "DaDi_new");

            //执行更新数据操作
            //第一个参数是表名，第二个是新数据，第三个是条件（相当于SQL中的where id =），第四个是条件值（字符串数组）
            db.update("user", contentValues, "id=?", new String[]{"1"});
        }
    }

    class selectListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DatabaseHelper databaseHelper = new DatabaseHelper(SQLiteActivity.this, "Database_test1", 2);
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            //用Cursor游标对查的数据进行数据遍历
            //query()的参数：第一个是所要查询表名，第二个是所要查询列名，第三个是条件语句，
            // 第四个是条件值，第五个是groupBy分组，第六个是Having分组结果限制，第七个是orderBy排序
            Cursor cursor = db.query("user", new String[]{"id", "name"}, "id=?", new String[]{"1"}, null, null, null);
            while (cursor.moveToNext()){
                //获取cursor中的字段为name的数据
                String name = cursor.getString(cursor.getColumnIndex("name"));
                System.out.println("name: " + name);
            }
        }
    }
}
