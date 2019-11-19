package com.anfly.summary.fragment;


import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.summary.R;
import com.anfly.summary.activity.WebViewActivity;
import com.anfly.summary.adapter.HomeAdapter;
import com.anfly.summary.base.BaseMvpFaragment;
import com.anfly.summary.bean.BannerBean;
import com.anfly.summary.bean.HomeComposeBean;
import com.anfly.summary.bean.HomeListBean;
import com.anfly.summary.presenter.ImpHomePrensenter;
import com.anfly.summary.view.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFaragment<ImpHomePrensenter, HomeView> implements HomeView, OnRefreshLoadMoreListener, HomeAdapter.OnItemClickListener {

    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.srl_home)
    SmartRefreshLayout srlHome;
    private ArrayList<BannerBean.DataBean> banners;
    private ArrayList<HomeListBean.DataBean.DatasBean> rereults;
    private HomeAdapter adapter;
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        banners = new ArrayList<>();
        rereults = new ArrayList<>();
        adapter = new HomeAdapter(banners, rereults, context);
        rvHome.setLayoutManager(new LinearLayoutManager(context));
        rvHome.setAdapter(adapter);
        srlHome.setOnRefreshLoadMoreListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        if (presenter != null) {
            presenter.getHomeUrl(page);
        }
    }

    @Override
    protected HomeView initMvpView() {
        return this;
    }

    @Override
    protected ImpHomePrensenter initMvpPresenter() {
        return new ImpHomePrensenter();
    }

    @Override
    public void onSuccess(HomeComposeBean homeComposeBean) {
        List<BannerBean.DataBean> banners = homeComposeBean.banners;
        List<HomeListBean.DataBean.DatasBean> results = homeComposeBean.results;

        this.banners.addAll(banners);
        this.rereults.addAll(results);
        adapter.notifyDataSetChanged();
        srlHome.finishLoadMore();
        srlHome.finishRefresh();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        srlHome.finishLoadMore();
        srlHome.finishRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        initData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (rereults != null && rereults.size() > 0) {
            rereults.clear();
            page = 1;
            initData();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", rereults.get(position).getLink());
        startActivity(intent);
    }
}
