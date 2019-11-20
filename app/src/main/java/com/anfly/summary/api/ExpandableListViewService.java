package com.anfly.summary.api;


import com.anfly.summary.bean.ExpandbleListviewBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ExpandableListViewService {
    String baseUrl = "https://www.wanandroid.com/";

    @GET("tree/json")
    Observable<ExpandbleListviewBean> getExpandableList();
}
