package com.anfly.summary.base;

import android.net.ParseException;
import android.util.Log;

import com.anfly.summary.R;
import com.anfly.summary.utils.SystemUtil;
import com.anfly.summary.utils.ToastUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {

    private BaseModel mModel;
    private Disposable disposable;

    public BaseObserver(BaseModel mModel) {
        this.mModel = mModel;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!SystemUtil.isNetworkConnected()) {
            ToastUtil.showShort(SummaryApplication.getApp().getString(R.string.net_unused));
            return;
        }

        disposable = d;
        mModel.addDisposable(disposable);
        Log.e("TAG", "onSubscribe mCompositeDisposable 长度:" + mModel.compositeDisposable.size());
    }

    @Override
    public void onNext(T value) {
        onSuccess(value);
        disposable.dispose();
        mModel.compositeDisposable.remove(disposable);
        Log.e("TAG", "onNext mCompositeDisposable 长度:" + mModel.compositeDisposable.size());
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFail(String error);


    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            //   HTTP错误
            onFail(SummaryApplication.getApp().getString(R.string.net_error));
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onFail(SummaryApplication.getApp().getString(R.string.conn_error));
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onFail(SummaryApplication.getApp().getString(R.string.conn_timeout));
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onFail(SummaryApplication.getApp().getString(R.string.parse_error));
        } else {
            if (e != null) {
                onFail(e.toString());
            } else {
                onFail(SummaryApplication.getApp().getString(R.string.unknow_error));
            }
        }
    }

    @Override
    public void onComplete() {
        Log.e("TAG", "BaseObserver onComplete()");
    }
}
