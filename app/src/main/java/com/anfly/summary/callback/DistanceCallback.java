package com.anfly.summary.callback;

import com.anfly.summary.base.BaseCallback;
import com.anfly.summary.bean.FoodBean;

public interface DistanceCallback extends BaseCallback<FoodBean, String> {
    @Override
    void onSuccess(FoodBean foodBean);

    @Override
    void onFail(String error);
}
