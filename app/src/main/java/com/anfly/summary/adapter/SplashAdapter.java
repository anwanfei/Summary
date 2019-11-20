package com.anfly.summary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.anfly.summary.R;

import java.util.ArrayList;

public class SplashAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> list;

    public SplashAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    //判断当前页面view是否与instantiateItem返回的Object一致
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //创建指定页面视图
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //加载视图
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp, null);
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(list.get(position));
        //添加加载视图到容器
        container.addView(view);
        return view;
    }

    //移除指定页面的view
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
