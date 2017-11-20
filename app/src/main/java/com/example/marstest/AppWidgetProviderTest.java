package com.example.marstest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;

/**
 * Created by 钧童 on 2017/9/18.
 */

public class AppWidgetProviderTest extends AppWidgetProvider {

    private static final String UPDATE_ACTION = "marstest.appwidget.UPDATE_APP_WIDGET";

    //到达指定更新时间或者用户向桌面添加AppWidget的时候调用
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        System.out.println("onUpdate");
        //循环用于对每个桌面的部件进行监控
        for(int i = 0; i < appWidgetIds.length; i++){
            //打开Activity
            //创建intent对象，用于从当前对象跳转到ExpandableListViewTest
            Intent intent = new Intent(context, ExpandableListViewTest.class);
            //创建PendingIntent,一个存储着Intent对象的预备对象
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            //用RemoteViews获取AppWidget桌面部件中的所有控件
            //RemoteView表示不与主程序在统一进程中的View
            //第一个参数是当前context所在包，第二个参数是AppWidget桌面部件的布局文件
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
            //设置监听事件，第一个参数表示监听的对象的控件的id，第二个参数是PendingIntent对象
            remoteViews.setOnClickPendingIntent(R.id.btnAppWidgetGetActivity, pendingIntent);
            //更新AppWidget第一个参数是更新的appWidget的id，第二个是RemoteViews对象
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

            //发送广播
            Intent intent1 = new Intent();
            //为Intent对象设置action
            intent1.setAction(UPDATE_ACTION);
            //使用getBroadcast方法得到一个PendingIntent对象
            //当该对象执行时会发送一个广播
            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, 0);
            RemoteViews remoteViews1 = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
            remoteViews1.setOnClickPendingIntent(R.id.btnAppWidgetGetBroadcast, pendingIntent1);
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews1);
        }


        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    //AppWidget被删除的时候调用
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        System.out.println("onDeleted");
        super.onDeleted(context, appWidgetIds);
    }
    //AppWidget第一次被创建时调用
    @Override
    public void onEnabled(Context context) {
        System.out.println("onEnabled");
        super.onEnabled(context);
    }
    //最后一个AppWidget被删除时调用
    @Override
    public void onDisabled(Context context) {
        System.out.println("onDisabled");
        super.onDisabled(context);
    }
    //接受广播事件
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive");
        String action = intent.getAction();
        if(UPDATE_ACTION.equals(action)){
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
            remoteViews.setImageViewResource(R.id.ivChange, R.drawable.after);
            remoteViews.setTextViewText(R.id.tvAppWidget, "The picture has changed");
            //创建AppWidgetManager
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            //创建ComponentName,代表的是整个AppWidget部件
            ComponentName componentName = new ComponentName(context, AppWidgetProviderTest.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }else {
            super.onReceive(context, intent);
        }
    }
}
