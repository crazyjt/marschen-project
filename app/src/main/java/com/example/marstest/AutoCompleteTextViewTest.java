package com.example.marstest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 钧童 on 2017/9/9.
 */

public class AutoCompleteTextViewTest extends Activity {
    private AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocompletetextview);

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);

        //List用于动态存储提示字符串
        List<String> list = new ArrayList<String>();
        list.add("Apple");
        list.add("App");
        //创建ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.autocompletetextview_list_item,R.id.tvAutoCompleteTextView, list);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}
