package com.anfly.summary.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    ArrayList<BaseModel> mModels = new ArrayList<BaseModel>();

    private V mMvpView;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void setView(V view) {
        this.mMvpView = view;
    }

    public void onDestroy() {
        //掐断网络请求
        if (mModels.size() > 0) {
            for (BaseModel baseModel : mModels) {
                baseModel.onDestroy();
            }
        }
        //打断P层和V层的联系
        if (mMvpView != null) {
            mMvpView = null;
        }
    }

    public void addModel(BaseModel model) {
        mModels.add(model);
    }

}
