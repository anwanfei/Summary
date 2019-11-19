package com.anfly.summary.callback;

import com.anfly.summary.base.BaseCallback;
import com.anfly.summary.bean.HomeComposeBean;

public interface HomeCallback extends BaseCallback<HomeComposeBean, String> {
    @Override
    void onSuccess(HomeComposeBean homeComposeBean);

    @Override
    void onFail(String error);
}
