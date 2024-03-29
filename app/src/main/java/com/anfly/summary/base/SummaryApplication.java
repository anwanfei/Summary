package com.anfly.summary.base;

import android.app.Application;

public class SummaryApplication extends Application {
    public static SummaryApplication app;

    public static SummaryApplication getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
