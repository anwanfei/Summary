package com.anfly.summary.callback;

import com.anfly.summary.base.BaseCallback;
import com.anfly.summary.bean.ExpandbleListviewBean;

public interface ExpandableListCallback extends BaseCallback<ExpandbleListviewBean, String> {
    @Override
    void onSuccess(ExpandbleListviewBean expandbleListviewBean);

    @Override
    void onFail(String error);
}
