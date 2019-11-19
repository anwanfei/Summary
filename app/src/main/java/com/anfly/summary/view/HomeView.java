package com.anfly.summary.view;

import com.anfly.summary.base.BaseView;
import com.anfly.summary.bean.HomeComposeBean;

public interface HomeView extends BaseView<HomeComposeBean, String> {
    @Override
    void onSuccess(HomeComposeBean homeComposeBean);

    @Override
    void onFail(String s);
}
