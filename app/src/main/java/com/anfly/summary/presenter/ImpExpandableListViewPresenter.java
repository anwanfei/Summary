package com.anfly.summary.presenter;

import com.anfly.summary.base.BasePresenter;
import com.anfly.summary.bean.ExpandbleListviewBean;
import com.anfly.summary.callback.ExpandableListCallback;
import com.anfly.summary.model.ImpExpandableListViewMlodel;
import com.anfly.summary.view.ExpandableListView;

public class ImpExpandableListViewPresenter extends BasePresenter<ExpandableListView, ImpExpandableListViewMlodel> implements ExpandableListViewPresenter, ExpandableListCallback {
    private ImpExpandableListViewMlodel impExpandableListViewMlodel;
    private ExpandableListView expandableListView;

    public ImpExpandableListViewPresenter(ExpandableListView expandableListView) {
        this.expandableListView = expandableListView;
    }

    @Override
    public void getExpandableList() {
        if (impExpandableListViewMlodel != null) {
            impExpandableListViewMlodel.getExpandableList(this);
        }
    }

    @Override
    public void onSuccess(ExpandbleListviewBean expandbleListviewBean) {
        if (expandableListView != null) {
            expandableListView.onSuccess(expandbleListviewBean);
        }
    }

    @Override
    public void onFail(String error) {
        if (expandableListView != null) {
            expandableListView.onFail(error);
        }
    }

    @Override
    protected void initModel() {
        impExpandableListViewMlodel = new ImpExpandableListViewMlodel();
    }
}
