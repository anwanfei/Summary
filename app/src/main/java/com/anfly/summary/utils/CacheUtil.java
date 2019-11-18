package com.anfly.summary.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtil {

    public static final String SHAREPRENFERENCE_NAME = "anfly";
    private static SharedPreferences sp;

    /**
     * 保存string
     * @param context
     * @param key
     * @param values
     */
    public static void putString(Context context, String key, String values) {
        sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        sp.edit().putString(key, values).commit();
    }

    /**
     * 获取string
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        return sp.getString(key, "");
    }

    /**
     * 保存boolean
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    //获取boolean
    public static boolean getBoolean(Context context, String key, boolean defult) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        }
        return sp.getBoolean(key, defult);
    }

    /**
     * 存储Int类型的值
     * @param mContext this
     * @param key      key
     * @param value    要存储的Int值
     */
    public static void putInt(Context mContext, String key, int value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }


    /**
     * 获取Int类型的值
     * @param mContext this
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static int getInt(Context mContext, String key, int defValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * 删除 单个 key
     * @param context
     * @param key
     */
    public static void deleShare(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }

    /**
     * 删除全部 key
     * @param context
     */
    public static void deleAll(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

}
