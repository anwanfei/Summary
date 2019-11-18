package com.anfly.summary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTimeUtil {

    //https://www.cnblogs.com/kevin-chen/p/7239650.html

    /**
     * 获取系统时间
     */
    public static void getCurrentDefaltData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.format(new Date());
    }

    /**
     * 获取系统时间
     */
    public static void getCurrentData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        format.format(new Date());
    }
}
