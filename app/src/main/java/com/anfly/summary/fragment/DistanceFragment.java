package com.anfly.summary.fragment;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.anfly.summary.R;
import com.anfly.summary.adapter.DistanceAdapter;
import com.anfly.summary.base.BaseMvpFaragment;
import com.anfly.summary.bean.FoodBean;
import com.anfly.summary.presenter.ImpDistancePresenter;
import com.anfly.summary.utils.ToastUtil;
import com.anfly.summary.view.DistanceView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistanceFragment extends BaseMvpFaragment<ImpDistancePresenter, DistanceView> implements DistanceView, OnRefreshLoadMoreListener {
    @BindView(R.id.rv_distance)
    RecyclerView rvDistance;
    @BindView(R.id.srl_distance)
    SmartRefreshLayout srlDistance;
    private int page = 1;
    private ArrayList<FoodBean.DataBean> list;
    private DistanceAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_distance;
    }

    @Override
    protected void initView() {
        super.initView();
        //fragment中添加选项菜单必须加这个
        setHasOptionsMenu(true);

        rvDistance.setLayoutManager(new LinearLayoutManager(context));

        list = new ArrayList<>();
        adapter = new DistanceAdapter(list, context);
        rvDistance.setAdapter(adapter);
        srlDistance.setOnRefreshLoadMoreListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0, 0, 0, "线性布局");
        menu.add(0, 1, 0, "网格布局");
        menu.add(0, 2, 0, "瀑布流");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                rvDistance.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case 1:
                rvDistance.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                break;
            case 2:
                rvDistance.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        super.initData();
        if (presenter != null) {
            presenter.getFeed(page);
        }
    }

    @Override
    protected DistanceView initMvpView() {
        return this;
    }

    @Override
    protected ImpDistancePresenter initMvpPresenter() {
        return new ImpDistancePresenter(this);
    }

    @Override
    public void onSuccess(FoodBean foodBean) {
        List<FoodBean.DataBean> data = foodBean.getData();
        list.addAll(data);
        adapter.notifyDataSetChanged();
        srlDistance.finishLoadMore().finishRefresh();
    }

    @Override
    public void onFail(String error) {
        ToastUtil.showLong(error);
        srlDistance.finishLoadMore().finishRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        initData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (list != null && list.size() > 0) {
            list.clear();
            page = 1;
            initData();
        }
    }
}
