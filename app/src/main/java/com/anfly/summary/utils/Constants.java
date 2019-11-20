package com.anfly.summary.utils;

import android.os.Environment;

import com.anfly.summary.base.SummaryApplication;

import java.io.File;

public interface Constants {
    String Url = "";
    String foodUrl = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1";
    String bannerUrl = "https://www.wanandroid.com/banner/json";
    String articleUrl = "https://www.wanandroid.com/article/list/";

    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "code" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY = "com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA = SummaryApplication.getApp().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
    String MODE = "mode";
    String FRAGMENT_POSITION = "FRAGMENT_POSITION";
    String JIANSHU_URL = "https://www.jianshu.com/u/5e15922197dd";
}
