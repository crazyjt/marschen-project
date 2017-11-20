package com.example.marstest.ContentProvider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.marstest.R;

/**
 * Created by 钧童 on 2017/7/19.
 */

public class ContentProviderActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentprovider);

        Button insert = (Button)findViewById(R.id.btnCPinsert);
        Button select = (Button)findViewById(R.id.btnCPselect);
        insert.setOnClickListener(new insertListener());
        select.setOnClickListener(new selectListener());
    }
    class insertListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ContentProviderMetaData.UserTableMetaData.USER_NAME, "DaDi");
            Uri uri = getContentResolver().insert(ContentProviderMetaData.UserTableMetaData.CONTENT_URI, contentValues);
            System.out.println("uri----->" + uri.toString());
        }
    }
    class selectListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Cursor cursor = getContentResolver().query(ContentProviderMetaData.UserTableMetaData.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()){
                System.out.println(cursor.getString(cursor.getColumnIndex(ContentProviderMetaData.UserTableMetaData.USER_NAME)));
            }
        }
    }
}
