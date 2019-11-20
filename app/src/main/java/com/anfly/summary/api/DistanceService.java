package com.anfly.summary.api;

import com.anfly.summary.bean.FoodBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DistanceService {
    //    http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=
    String baseUrl = "http://www.qubaobei.com/";

    @GET("ios/cf/dish_list.php?stage_id=1&limit=20")
    Observable<FoodBean> getFeed(@Query("page") int page);
}
