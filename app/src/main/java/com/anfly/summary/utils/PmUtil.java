package com.anfly.summary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class PmUtil {

    public static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    public static final String[] PERMISSIONS_MY_CTID = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    /**
     * 列表中的权限是否全部开通
     *
     * @param pmList
     * @param activity
     * @return
     */
    public static boolean havePm(List<String> pmList, Activity activity) {
        for (String pm : pmList) {
            int permissionState = ActivityCompat.checkSelfPermission(activity, pm);
            if (permissionState != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 请求开通权限
     *
     * @param pmList
     * @param requestCode
     * @param activity
     */
    public static void requestPm(List<String> pmList, int requestCode, Activity activity) {
        List<String> needPm = checkPm(pmList, activity);
        if (needPm.size() > 0) {
            String[] pmsArray = needPm.toArray(new String[needPm.size()]);
            ActivityCompat.requestPermissions(activity, pmsArray, requestCode);
        }
    }

    /**
     * 获取没有开通的权限列表
     *
     * @param pmList
     * @param activity
     * @return
     */
    public static List<String> checkPm(List<String> pmList, Activity activity) {
        List<String> needPm = new ArrayList<String>();
        for (String pm : pmList) {
            int permissionState = ActivityCompat.checkSelfPermission(activity, pm);
            if (permissionState != PackageManager.PERMISSION_GRANTED) {
                needPm.add(pm);
            }
        }
        return needPm;
    }
}