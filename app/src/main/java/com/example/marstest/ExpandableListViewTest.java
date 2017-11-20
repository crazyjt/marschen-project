package com.example.marstest;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 钧童 on 2017/9/15.
 */

public class ExpandableListViewTest extends ExpandableListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablelistview);

        //定义group的数据组
        List<Map<String, String>> groups = new ArrayList<Map<String, String>>();
        Map<String, String> group1 = new HashMap<>();
        group1.put("group", "group1");
        Map<String, String> group2 = new HashMap<>();
        group2.put("group", "group2");
        //将两个组加入列表
        groups.add(group1);
        groups.add(group2);

        //定义group1的内容
        List<Map<String, String>> child1 = new ArrayList<Map<String, String>>();
        Map<String, String> child1data1 = new HashMap<>();
        child1data1.put("child", "child1data1");
        Map<String, String> child1data2 = new HashMap<>();
        child1data2.put("child", "child2data2");
        child1.add(child1data1);
        child1.add(child1data2);

        //定义group2的内容
        List<Map<String, String>> child2 = new ArrayList<Map<String, String>>();
        Map<String, String> child2data1 = new HashMap<>();
        child2data1.put("child", "child2data1");
        Map<String, String> child2data2 = new HashMap<>();
        child2data2.put("child", "child2data2");
        child2.add(child2data1);
        child2.add(child2data2);

        //定义list用于存储两个组的所有child数据
        List<List<Map<String, String>>> childs = new ArrayList<List<Map<String,String>>>();
        childs.add(child1);
        childs.add(child2);

        //生成一个SimpleExpandableListAdapter对象
        //参数1：context
        //参数2：一级条目数据源
        //参数3：一级条目布局
        //参数4：一级条目数据的key值
        //参数5：一级条目数据显示的控件
        //参数6：二级条目数据源
        //参数7：二级条目布局
        //参数8：二级条目数据的key值
        //参数9：二级条目数据显示的控件
        SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(this,
                                            groups, R.layout.expandablelistview_group, new String[]{"group"},new int[]{R.id.tvExpandableListViewGroup},
                                            childs, R.layout.expandablelistview_child, new String[]{"child"}, new int[]{R.id.tvExpandableListViewChild});
        //将SimpleExpandableListAdapter对象设置给当前的ExpandableListAcivity
        setListAdapter(sela);
    }
}
