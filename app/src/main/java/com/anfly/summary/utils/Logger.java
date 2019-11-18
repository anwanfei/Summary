package com.anfly.summary.utils;

import android.util.Log;

import io.reactivex.android.BuildConfig;


/**
 * Created by asus on 2019/3/5.
 */

public class Logger {
    public static void logD(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "logD: " + msg);
        }
    }

    public static void println(String msg) {
        if (BuildConfig.DEBUG) {
            System.out.println(msg);
        }
    }

    public static void print(String msg) {
        if (BuildConfig.DEBUG) {
            System.out.println(msg);
        }
    }
}
