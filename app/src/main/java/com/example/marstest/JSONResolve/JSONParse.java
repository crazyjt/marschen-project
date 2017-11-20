package com.example.marstest.JSONResolve;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.marstest.R;

/**
 * Created by 钧童 on 2017/10/12.
 */

public class JSONParse extends Activity {
    private Button json;
    private Button gson;
    private Button jsons;
    private String jsonData = "{\"name\":\"zhangsan\",\"age\":20}";
    private String jsonArray = "[{\"name\":\"zhangsan\",\"age\":20},{\"name\":\"lisi\",\"age\":25}]";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        json = (Button)findViewById(R.id.btnJsonParse);
        gson = (Button)findViewById(R.id.btnGson);
        jsons = (Button)findViewById(R.id.btnJsonArray);
        json.setOnClickListener(new JsonListener());
        gson.setOnClickListener(new GsonListener());
        jsons.setOnClickListener(new JsonArrayListener());
    }

    class JsonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            JsonUtils jsonUtils = new JsonUtils();
            jsonUtils.parseJson(jsonArray);
        }
    }

    class GsonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            GsonUtils gsonUtils = new GsonUtils();
            gsonUtils.parseUserFromJson(jsonData);
        }
    }

    class JsonArrayListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            JsonArrayUtils jsonArrayUtils = new JsonArrayUtils();
            jsonArrayUtils.parseUserFromJsonArray(jsonArray);
        }
    }
}
