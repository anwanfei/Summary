package com.anfly.summary;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.anfly.summary.base.BaseActivity;
import com.anfly.summary.base.BaseFragment;
import com.anfly.summary.fragment.DistanceFragment;
import com.anfly.summary.fragment.HomeFragment;
import com.anfly.summary.fragment.MineFragment;
import com.anfly.summary.fragment.WheelFragment;
import com.anfly.summary.utils.Constants;
import com.anfly.summary.utils.FragmentType;
import com.anfly.summary.utils.SharedPreferencesUtil;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    private FragmentManager fragmentManager;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment fromFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        //NavigationView隐藏滑动滚动条
        NavigationMenuView navigationMenuItemView = (NavigationMenuView) nv.getChildAt(0);
        if (navigationMenuItemView != null) {
            navigationMenuItemView.setVerticalScrollBarEnabled(false);
        }

        toolbar.setTitle(getResources().getString(R.string.home));
        toolbar.setSubtitle(getResources().getString(R.string.fly));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setLogo(R.mipmap.a);
        setSupportActionBar(toolbar);

        nv.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        //初识化fragment
        initFragment();

        //默认显示homefragment
        defaultHomeFragment();
    }

    private void defaultHomeFragment() {
        int fragment_position = (int) SharedPreferencesUtil.getParam(this, Constants.FRAGMENT_POSITION, FragmentType.TYPE_ZHIHU);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragments.get(position);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_container, fragment);
        }
        toolbar.setTitle(getResources().getString(R.string.home));
        transaction.commit();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DistanceFragment());
        fragments.add(new WheelFragment());
        fragments.add(new MineFragment());
    }

    @Override
    protected void initListener() {
        super.initListener();
        //主界面在x轴跟随侧滑菜单移动
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = drawerView.getRight();
                llMain.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        //获取头部的点击事件
        View headerView = nv.getHeaderView(0);
        ImageView iv_header = headerView.findViewById(R.id.iv_header);
        TextView tv_header = headerView.findViewById(R.id.tv_header);
        iv_header.setOnClickListener(this);

        //radiobutton点击事件
        rg.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this, "头部点击事件", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
//                setBottomTab(0, getResources().getString(R.string.home));
                switchFragment(0, R.string.home);
                break;
            case R.id.rb_distance:
//                setBottomTab(1, getResources().getString(R.string.distance));
                switchFragment(1, R.string.distance);
                break;
            case R.id.rb_wheel:
//                setBottomTab(2, getResources().getString(R.string.wheel));
                switchFragment(2, R.string.wheel);
                break;
            case R.id.rb_mine:
//                setBottomTab(3, getResources().getString(R.string.mine));
                switchFragment(3, R.string.mine);
                break;
            default:
//                setBottomTab(0, getResources().getString(R.string.home));
                switchFragment(0, R.string.home);
                break;
        }
    }

    private void switchFragment(int position, int title) {

        //获取事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //隐藏上一个fragment
        BaseFragment lastFragment = fragments.get(this.position);
        if (lastFragment != null) {
            transaction.hide(lastFragment);
        }

        //显示当前fragment
        BaseFragment curFragment = fragments.get(position);
        if (!curFragment.isAdded()) {
            transaction.add(R.id.fl_container, curFragment);
        }
        transaction.show(curFragment).commit();

        //切换完成之后,当前的fragmnet就是下一次切换要隐藏的Fragmnet
        this.position = position;

        //toolbar设置标题
        toolbar.setTitle(getResources().getString(title));
    }
}
