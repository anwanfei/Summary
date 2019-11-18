package com.anfly.summary.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author Anfly
 * @date 2019/3/5.
 * description：尺寸工具类：包括获取屏幕宽高，密度，dp、px互转,px、sp互转
 */
public class ScreenUtil {
    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕密度
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
