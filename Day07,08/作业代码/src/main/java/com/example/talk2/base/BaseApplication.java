package com.example.talk2.base;

import android.app.Application;
import android.content.Context;

import com.example.talk2.helper.SharedPrefHelper;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;

import static cn.jpush.im.android.api.JMessageClient.FLAG_NOTIFY_SILENCE;

/**
 * Created by wapchief on 2017/4/13 0013 上午 11:23.
 * 描述：自定义Application
 */
public class BaseApplication extends Application {


    public static BaseApplication baseApplication;
    private Context mContext;
//    public MySQLiteOpenHelper helper;
//    private DaoMaster master;
    private SharedPrefHelper sharedPrefHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
//        MultiDex.install(this);
        sharedPrefHelper = SharedPrefHelper.getInstance();
        sharedPrefHelper.setRoaming(true);
        //开启极光调试
        JPushInterface.setDebugMode(true);
        mContext = BaseApplication.this;
        //实例化极光推送
        JPushInterface.init(mContext);
        //实例化极光IM,并自动同步聊天记录
        JMessageClient.init(getApplicationContext(), true);
        JMessageClient.setDebugMode(true);
        //初始化极光sms
//        SMSSDK.getInstance().initSdk(mContext);
        //初始化数据库
//        setupDatabase();
        //通知管理,通知栏开启，其他关闭
        JMessageClient.setNotificationFlag(FLAG_NOTIFY_SILENCE);
        //初始化utils
//        Utils.init(this);
        //推送状态
        initJPush2();
        //初始化统计
        JAnalyticsInterface.init(mContext);
        JAnalyticsInterface.setDebugMode(true);

    }

    private void initJPush2() {
                sharedPrefHelper.setMusic(false);
                sharedPrefHelper.setVib(false);
                sharedPrefHelper.setAppKey("b47a37f342eba5f9fbcd1961");
    }

//    private void setupDatabase() {
//        //是否开启调试
//        MigrationHelper.DEBUG = true;
//        QueryBuilder.LOG_SQL = true;
//        QueryBuilder.LOG_VALUES = true;
//        //数据库升级
//        helper = new MySQLiteOpenHelper(mContext, "text");
//        master = new DaoMaster(helper.getWritableDb());
//
//    }
}
