package com.anfly.summary.utils;

import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

/**
 * @author anfly
 * @date 2019/7/16.
 * description：使用这个类需要把style设置成日夜间的样式:Theme.AppCompat.DayNight.NoActionBar
 */
public class UIModeUtil {
    /**
     * 夜间模式切换
     */
    public static void changeModeUI(AppCompatActivity activity) {
        int currentNightMode = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//
//        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
//            //日间模式,切成夜间模式
//            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            SharedPreferencesUtil.setParam(activity, Constants.MODE, AppCompatDelegate.MODE_NIGHT_YES);
//            SummaryApplication.mMode = AppCompatDelegate.MODE_NIGHT_YES;
//        } else {
//            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            SharedPreferencesUtil.setParam(activity, Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
//            BaseApplication.mMode = AppCompatDelegate.MODE_NIGHT_NO;
//        }
    }

    /**
     * 设置当前的模式
     *
     * @param activity
     */
    public static void setActModeFromSp(AppCompatActivity activity) {
        int mode = (int) SharedPreferencesUtil.getParam(activity, Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        activity.getDelegate().setLocalNightMode(mode);
    }

    /**
     * 设置当前的模式
     *
     * @param activity
     */
    public static void setActModeUseMode(AppCompatActivity activity, int mode) {
        activity.getDelegate().setLocalNightMode(mode);
    }

    /**
     * 通过App设置模式
     *
     * @param mode
     */
    public static void setAppMode(int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
    }
}
