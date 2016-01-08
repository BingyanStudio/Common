package com.example.myapplication;

import android.app.Application;

import net.bingyan.common.Common;

/**
 * Created by 2bab on 15/12/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Common.init(this);
    }
}
