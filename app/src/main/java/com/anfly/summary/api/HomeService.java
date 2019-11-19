package com.anfly.summary.api;

import com.anfly.summary.bean.BannerBean;
import com.anfly.summary.bean.HomeBean;
import com.anfly.summary.bean.HomeListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeService {
    String foodUrl = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1";
    String bannerUrl = "https://www.wanandroid.com/banner/json";
    String articleUrl = "https://www.wanandroid.com/article/list/";

    //    String baseUrl = "https://gitee.com/AnDrFly/study/raw/master/";
    String baseUrl = "https://www.wanandroid.com/";

    @GET("home")
    Observable<HomeBean> getHomeUrl();

    @GET("banner/json")
    Observable<BannerBean> getHomeBanner();

    @GET("article/list/{page}/json")
    Observable<HomeListBean> getHomeList(@Path("page") int page);
}
