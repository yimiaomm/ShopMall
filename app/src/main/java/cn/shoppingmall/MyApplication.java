package cn.shoppingmall;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import cn.shoppingmall.activity.ActivityManager;

/**
 * developer: liyongfeng
 * created on: 2017/9/1 22:58
 * description:
 */
public class MyApplication extends Application {

    private static Context appCtx;
    public static Gson gson = new Gson();
    private static ActivityManager activityManager = null;
    public static Context getAppCtx() {

        return appCtx;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appCtx = this;
        activityManager = ActivityManager.getInstance();
    }
    public static ActivityManager getActivityManager() {
        return activityManager;
    }
}
