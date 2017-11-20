package com.example.marstest;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 钧童 on 2017/9/27.
 */

public class ListViewAnimationByJava extends ListActivity {
    private ListView listView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_animation_by_java);
        listView = getListView();
        listView.setAdapter(buildAdapter());

        //通过动画设置的xml文件创建Animation对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.list_anim);
        //通过Animation对象创建LayoutAnimationController对象
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        //设置LayoutAnimationController对象的顺序
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //为动画间隔设置时间
        layoutAnimationController.setDelay(0.5f);
        //为控件设置LayoutAnimation对象
        listView.setLayoutAnimation(layoutAnimationController);
    }

    //自定义函数生成Adapter对象和存入数据
    private ListAdapter buildAdapter(){
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> m1 = new HashMap<>();
        m1.put("user_name", "张三");
        m1.put("user_sex", "男");
        HashMap<String, String> m2 = new HashMap<>();
        m2.put("user_name", "李四");
        m2.put("user_sex", "男");
        HashMap<String, String> m3 = new HashMap<>();
        m3.put("user_name", "王五");
        m3.put("user_sex", "女");
        list.add(m1);
        list.add(m2);
        list.add(m3);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
                R.layout.listview_animation_item,
                new String[]{"user_name", "user_sex"} ,
                new int[]{R.id.tvListViewAnimationName, R.id.tvListViewAnimationSex});
        return simpleAdapter;
    }
}
