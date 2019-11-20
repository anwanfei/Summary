package com.anfly.summary.model;

import com.anfly.summary.api.ExpandableListViewService;
import com.anfly.summary.base.BaseModel;
import com.anfly.summary.base.BaseObserver;
import com.anfly.summary.bean.ExpandbleListviewBean;
import com.anfly.summary.callback.ExpandableListCallback;
import com.anfly.summary.utils.HttpUtil;
import com.anfly.summary.utils.RxUtil;

public class ImpExpandableListViewMlodel extends BaseModel implements ExpandableListViewMlodel {

    @Override
    public void getExpandableList(ExpandableListCallback expandableListCallback) {
        HttpUtil.getHttpUtil().getServer(ExpandableListViewService.baseUrl, ExpandableListViewService.class)
                .getExpandableList()
                .compose(RxUtil.rxObservableTransformer())
                .subscribe(new BaseObserver<ExpandbleListviewBean>(this) {
                    @Override
                    protected void onSuccess(ExpandbleListviewBean expandbleListviewBean) {
                        expandableListCallback.onSuccess(expandbleListviewBean);
                    }

                    @Override
                    protected void onFail(String error) {
                        expandableListCallback.onFail(error);
                    }
                });
    }
}
