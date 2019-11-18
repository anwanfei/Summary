package com.anfly.summary.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anfly
 * @date 2019/3/5.
 * description：gson工具类
 */
public class GsonUtils {
    //	private static Gson gson = new Gson();
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    /*
     * json字符串转换为单个对象
     */
    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
        }
        return t;
    }

    /*
     * json字符串转换为对象集合
     */
    public static <T> List<T> getObjectList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
        }
        return list;
    }

    /*
     * 将一个对象序列化到一个json字符串
     */
    public static String toJsonString(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            return "";
        }
    }
}
