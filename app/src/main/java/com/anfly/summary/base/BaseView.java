package com.anfly.summary.base;

public interface BaseView<T, K> {

    void onSuccess(T t);

    void onFail(K k);

}
