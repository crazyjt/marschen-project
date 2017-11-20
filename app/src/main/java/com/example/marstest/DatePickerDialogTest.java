package com.example.marstest;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Created by 钧童 on 2017/9/9.
 */

public class DatePickerDialogTest extends Activity {
    private static final int DATE_PICKER_ID = 1;
    private Button showDialog;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepickerdialog);
        showDialog = (Button)findViewById(R.id.btnShowDatePickerDialog);

        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog方法会自动调用onCreateDialog方法,显示日期选择对话框
                showDialog(DATE_PICKER_ID);
            }
        });
    }

    //监听器，监听用户点下DatePickerDialog的set按钮时，所设置的年月日
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            System.out.println(year + "-" + month + "-" + dayOfMonth);
        }
    };

    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, onDateSetListener, 2017, 8, 9);
        }
        return null;
    }
}
