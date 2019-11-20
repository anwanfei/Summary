package com.anfly.summary.view;

import com.anfly.summary.base.BaseView;
import com.anfly.summary.bean.FoodBean;

public interface DistanceView extends BaseView<FoodBean, String> {
    @Override
    void onSuccess(FoodBean foodBean);

    @Override
    void onFail(String error);
}
