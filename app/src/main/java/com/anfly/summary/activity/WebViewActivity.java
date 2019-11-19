package com.anfly.summary.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.anfly.summary.R;
import com.anfly.summary.base.BaseActivity;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initData() {
        super.initData();
        String url = getIntent().getStringExtra("url");
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient());
    }
}
