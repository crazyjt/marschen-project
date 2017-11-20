package com.example.marstest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by 钧童 on 2017/7/30.
 */

public class ParcelTest extends Activity {

    //定义一个Parcel对象（一个存放数据的包）,而且是以指针作为索引，以顺序链方式存储
    android.os.Parcel parcel = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel);

        //生成parcel对象
        parcel = Parcel.obtain();

        Button save = (Button)findViewById(R.id.btnServiceSave);
        Button get = (Button)findViewById(R.id.btnServiceGet);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用writeXXX()方法向parcel中写入数据
                parcel.writeString("abc");
                parcel.writeInt(123);
                parcel.writeFloat(1.0f);
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从parcel中取出数据
                //将指针移动到第一位
                parcel.setDataPosition(0);

                //用readXXX()方法读取parcel中的数据
                //数据的读取顺序必须与数据的存放顺序一致
                String str = parcel.readString();
                int i = parcel.readInt();
                float f = parcel.readFloat();

                System.out.println("str----->" + str);
                System.out.println("i----->" + i);
                System.out.println("f----->" + f);
            }
        });
    }
}
