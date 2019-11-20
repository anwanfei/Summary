package com.anfly.summary.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //一个抽象方法，子类必须实现
        setContentView(getLayoutId());

        //butterknife绑定
        ButterKnife.bind(this);

        //四个普通方法，子类可以选择性的实现
        initMvp();
        initView();
        initData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected void initMvp() {
    }

    protected void initListener() {
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected <T> void goActivity(Class<T> mClass) {
        Intent intent = new Intent(this, mClass );
        startActivity(intent);
    }

}
