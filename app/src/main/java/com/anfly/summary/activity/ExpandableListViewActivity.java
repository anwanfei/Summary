package com.anfly.summary.activity;

import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.widget.Toolbar;

import com.anfly.summary.R;
import com.anfly.summary.adapter.ExpandableListViewAdapter;
import com.anfly.summary.base.BaseMvpActivity;
import com.anfly.summary.bean.ExpandbleListviewBean;
import com.anfly.summary.presenter.ImpExpandableListViewPresenter;
import com.anfly.summary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExpandableListViewActivity extends BaseMvpActivity<ImpExpandableListViewPresenter, com.anfly.summary.view.ExpandableListView> implements com.anfly.summary.view.ExpandableListView, ExpandableListView.OnChildClickListener {

    @BindView(R.id.elv)
    ExpandableListView elv;
    @BindView(R.id.toolbar_elv)
    Toolbar toolbarElv;
    private ArrayList<ExpandbleListviewBean.DataBean> list;
    private ExpandableListViewAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expandable_list_view;
    }

    @Override
    protected void initView() {
        super.initView();

        setSupportActionBar(toolbarElv);
        toolbarElv.setNavigationIcon(R.drawable.ic_stat_name);
        toolbarElv.setNavigationOnClickListener(view -> finish());

        list = new ArrayList<>();
        adapter = new ExpandableListViewAdapter(list, this);
        elv.setAdapter(adapter);
        elv.setOnChildClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        if (presenter != null) {
            presenter.getExpandableList();
        }
    }

    @Override
    protected com.anfly.summary.view.ExpandableListView initMvpView() {
        return this;
    }

    @Override
    protected ImpExpandableListViewPresenter initPresenter() {
        return new ImpExpandableListViewPresenter(this);
    }

    @Override
    public void onSuccess(ExpandbleListviewBean expandbleListviewBean) {
        List<ExpandbleListviewBean.DataBean> data = expandbleListviewBean.getData();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        ToastUtil.showShort(error);
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        ToastUtil.showShort(list.get(i).getChildren().get(i1).getName());
        return false;
    }
}
