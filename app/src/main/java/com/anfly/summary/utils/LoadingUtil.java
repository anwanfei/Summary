package com.anfly.summary.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;

import com.anfly.summary.R;

public class LoadingUtil {
    private static LoadingUtil loadingUtil;
    private Context context;
    private Dialog dialog;

    public static LoadingUtil getLoadingUtil() {
        if (loadingUtil == null) {
            synchronized (LoadingUtil.class) {
                if (loadingUtil == null) {
                    loadingUtil = new LoadingUtil();
                }
            }
        }
        return loadingUtil;
    }

    public void showLoading(Context context) {
        if (dialog == null ) {

            this.context = context;
            dialog = new Dialog(context, R.style.LoadingStyle);

            dialog.setContentView(R.layout.dialog_loading);

            dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
            dialog.setCancelable(true);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.show();

        }
    }

    public void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.hide();
        }
    }
}


