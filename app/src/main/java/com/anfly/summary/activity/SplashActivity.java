package com.anfly.summary.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.anfly.summary.MainActivity;
import com.anfly.summary.R;
import com.anfly.summary.adapter.SplashAdapter;
import com.anfly.summary.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.vp_splash)
    ViewPager vpSplash;
    @BindView(R.id.dot1)
    ImageView dot1;
    @BindView(R.id.dot2)
    ImageView dot2;
    @BindView(R.id.dot3)
    ImageView dot3;
    @BindView(R.id.dot)
    LinearLayout dot;
    @BindView(R.id.btn_jump)
    TextView btnJump;

    //底部小图片数组
    private ArrayList<ImageView> dotArray;
    //小圆点id
    private int[] ids = {R.id.dot1, R.id.dot2, R.id.dot3};
    private ArrayList<String> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        dotArray = new ArrayList<>();
        dotArray.add(dot1);
        dotArray.add(dot2);
        dotArray.add(dot3);

        list = new ArrayList<>();
        list.add("页面一");
        list.add("页面二");
        list.add("页面三");

        SplashAdapter adapter = new SplashAdapter(this, list);
        vpSplash.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        vpSplash.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("vp", "滑动中=====position:" + position + "   positionOffset:" +
                        positionOffset + "   positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("vp", "显示页改变=====postion:" + position);
                if (position == 2) {
                    btnJump.setVisibility(View.VISIBLE);
                } else {
                    btnJump.setVisibility(View.GONE);
                }

                // 设置底部小点选中状态
                for (int i = 0; i < list.size(); i++) {
                    if (position == i) {
                        dotArray.get(i).setImageResource(R.drawable.slide_adv_selected);
                    } else {
                        dotArray.get(i).setImageResource(R.drawable.slide_adv_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        Log.e("vp", "状态改变=====SCROLL_STATE_IDLE====静止状态");
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Log.e("vp", "状态改变=====SCROLL_STATE_DRAGGING==滑动状态");
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        Log.e("vp", "状态改变=====SCROLL_STATE_SETTLING==滑翔状态");

                }
            }
        });
    }

    @OnClick(R.id.btn_jump)
    public void onViewClicked() {
        goActivity(MainActivity.class);
        finish();
    }
}
