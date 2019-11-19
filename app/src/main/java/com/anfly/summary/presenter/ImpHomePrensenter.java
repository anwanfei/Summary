package com.anfly.summary.presenter;

import com.anfly.summary.base.BasePresenter;
import com.anfly.summary.bean.HomeComposeBean;
import com.anfly.summary.callback.HomeCallback;
import com.anfly.summary.model.ImpHomeModel;
import com.anfly.summary.view.HomeView;

public class ImpHomePrensenter extends BasePresenter<HomeView, ImpHomeModel> implements HomePresenter, HomeCallback {
    private ImpHomeModel impHomeModel;

    @Override
    protected void initModel() {
        impHomeModel = new ImpHomeModel();
    }

    @Override
    public void getHomeUrl(int page) {
        if (impHomeModel != null) {
            impHomeModel.getHomeUrl(page,this);
        }
    }

    @Override
    public void onSuccess(HomeComposeBean homeComposeBean) {
        if (mMvpView != null) {
            mMvpView.onSuccess(homeComposeBean);
        }
    }

    @Override
    public void onFail(String error) {
        if (mMvpView != null) {
            mMvpView.onFail(error);
        }
    }
}
