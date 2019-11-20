package com.anfly.summary.model;

import com.anfly.summary.api.DistanceService;
import com.anfly.summary.base.BaseModel;
import com.anfly.summary.base.BaseObserver;
import com.anfly.summary.bean.FoodBean;
import com.anfly.summary.callback.DistanceCallback;
import com.anfly.summary.utils.HttpUtil;
import com.anfly.summary.utils.RxUtil;

public class ImpDistanceModel extends BaseModel implements DistanceModel {
    @Override
    public void getFeed(int page, DistanceCallback distanceCallback) {
        HttpUtil.getHttpUtil().getServer(DistanceService.baseUrl, DistanceService.class).getFeed(page).compose(RxUtil.rxObservableTransformer())
                .subscribe(new BaseObserver<FoodBean>(this) {
                    @Override
                    protected void onSuccess(FoodBean foodBean) {
                        distanceCallback.onSuccess(foodBean);
                    }

                    @Override
                    protected void onFail(String error) {
                        distanceCallback.onFail(error);
                    }
                });
    }
}
