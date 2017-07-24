package com.deilsky.activies;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by deilsky on 2016/8/15.
 */
public class App extends Application {
    private static App instance;
    public Context appContext;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = this;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
