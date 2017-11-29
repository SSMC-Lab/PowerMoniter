package com.wshadow.powermonitoring;

import android.app.Application;
import android.content.Context;

/**
 * Created by WelkinShadow on 2017/11/15.
 */

public class MyApplication extends Application {
    private static MyApplication mApplication;

    /**
     * 获取Context
     */
    public static Context getContext(){
        return mApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mApplication = this;
    }
}
