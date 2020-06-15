package com.example.timeandclass;

import android.app.Application;

import com.example.timeandclass.helper.SharedPrefHelper;

public class BaseApplication extends Application {
    public static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;

    }
}

