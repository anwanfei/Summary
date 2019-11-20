package com.anfly.summary.activity;

import android.graphics.drawable.ColorDrawable;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class CollectionAcitivty extends BaseActivity implements CollectionAdapter.OnItemClickListener, CollectionAdapter.OnItemLongClickListener {

    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;
    @BindView(R.id.toolbar_collection)
    Toolbar toolbarCollection;
    @BindView(R.id.ll_collection)
    LinearLayout llCollection;
    private List<FoodDbBean> list;
    private CollectionAdapter adapter;
    private int position;

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
        adapter.setOnItemLongClickListener(this);
        registerForContextMenu(rvCollection);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {
        this.position = position;
    }

    private void showPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_insert, null);
        Button ben_cancel = view.findViewById(R.id.btn_cancel);
        Button btn_ok = view.findViewById(R.id.btn_ok);
        final EditText et_name = view.findViewById(R.id.et_name);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //点击外框消失
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);

        //设置全屏阴影
        backgroundAlpha(0.5f);

        //设置进出场动画
        popupWindow.setAnimationStyle(R.style.popStyle);

        //显示pop
        popupWindow.showAtLocation(llCollection, Gravity.CENTER, 0, 0);

        //点击事件
        btn_ok.setOnClickListener(view1 -> {
            FoodDbBean foodDbBean = list.get(position);
            boolean delete = GreendaoUtil.getGreendaoUtil().delete(foodDbBean);
            if (delete) {
                list.remove(position);
                adapter.notifyDataSetChanged();
                ToastUtil.showShort("删除成功");
            } else {
                ToastUtil.showShort("删除失败");
            }
            popupWindow.dismiss();
        });
        ben_cancel.setOnClickListener(view1 -> popupWindow.dismiss());

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }

    private void backgroundAlpha(float alpha) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = alpha;
        getWindow().setAttributes(attributes);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 1, 0, "修改");
        menu.add(0, 2, 0, "删除");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                ToastUtil.showShort("不可修改");
                break;
            case 2:
                showPop();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
