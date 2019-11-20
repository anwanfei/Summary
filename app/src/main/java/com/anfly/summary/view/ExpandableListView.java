package com.anfly.summary.view;

import com.anfly.summary.base.BaseView;
import com.anfly.summary.bean.ExpandbleListviewBean;

public interface ExpandableListView extends BaseView<ExpandbleListviewBean, String> {
    @Override
    void onSuccess(ExpandbleListviewBean expandbleListviewBean);

    @Override
    void onFail(String error);
}
