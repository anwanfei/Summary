package com.anfly.summary.presenter;

import com.anfly.summary.base.BasePresenter;
import com.anfly.summary.bean.FoodBean;
import com.anfly.summary.callback.DistanceCallback;
import com.anfly.summary.model.ImpDistanceModel;
import com.anfly.summary.view.DistanceView;

public class ImpDistancePresenter extends BasePresenter<DistanceView, ImpDistanceModel> implements DistancePresenter, DistanceCallback {

    private ImpDistanceModel impDistanceModel;
    private DistanceView distanceView;

    public ImpDistancePresenter(DistanceView distanceView) {
        this.distanceView = distanceView;
    }

    @Override
    public void getFeed(int page) {
        if (impDistanceModel != null) {
            impDistanceModel.getFeed(page, this);
        }
    }


    @Override
    public void onSuccess(FoodBean foodBean) {
        if (distanceView != null) {
            distanceView.onSuccess(foodBean);
        }
    }

    @Override
    public void onFail(String error) {
        if (distanceView != null) {
            distanceView.onFail(error);
        }
    }

    @Override
    protected void initModel() {
        impDistanceModel = new ImpDistanceModel();
    }
}
