package com.anfly.summary.activity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.summary.R;
import com.anfly.summary.adapter.CollectionAdapter;
import com.anfly.summary.base.BaseActivity;
import com.anfly.summary.bean.FoodDbBean;
import com.anfly.summary.db.GreendaoUtil;
import com.anfly.summary.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

public class CollectionAcitivty extends BaseActivity implements CollectionAdapter.OnItemClickListener {

    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;
    @BindView(R.id.toolbar_collection)
    Toolbar toolbarCollection;
    private List<FoodDbBean> list;
    private CollectionAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
        super.initView();

        setSupportActionBar(toolbarCollection);
        //toolbar设置侧滑菜单小图标
        toolbarCollection.setNavigationIcon(R.drawable.ic_stat_name);
        //toolbar设置侧滑菜单小图标设置点击事件
        toolbarCollection.setNavigationOnClickListener(view -> finish());

        rvCollection.setLayoutManager(new LinearLayoutManager(this));
        list = GreendaoUtil.getGreendaoUtil().query();
        adapter = new CollectionAdapter(list, this);
        rvCollection.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(int position) {
        FoodDbBean foodDbBean = list.get(position);
        boolean delete = GreendaoUtil.getGreendaoUtil().delete(foodDbBean);
        if (delete) {
            list.remove(position);
            adapter.notifyDataSetChanged();
            ToastUtil.showShort("删除成功");
        } else {
            ToastUtil.showShort("删除失败");
        }
    }
}
