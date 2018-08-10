package com.example.lancer.gankl;

import android.app.Application;
import android.content.Context;

import com.example.lancer.gankl.util.Constants;

/**
 * author: Lancer
 * date：2018/8/9
 * des:
 * email:tyk790406977@126.com
 */

public class MyApplication extends Application {
    private static MyApplication instance = null;
    private static Context sContext;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
