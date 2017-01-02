package com.example.androidthings.myproject.application;

import android.app.Application;

import java.util.TimeZone;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Stockholm"));
    }
}
