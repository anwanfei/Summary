package com.anfly.summary.base;

import android.app.Application;

public class SummaryApplication extends Application {
    public SummaryApplication app;

    public SummaryApplication getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
