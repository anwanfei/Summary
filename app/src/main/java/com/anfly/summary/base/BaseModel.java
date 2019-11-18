package com.anfly.summary.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void onDestroy() {
        //切换所有的Disposable对象
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable != null) {
            compositeDisposable.add(disposable);
        }
    }
}
