package com.anfly.summary.base;

public interface BaseCallback<T, K> {

    void onSuccess(T t);

    void onFail(K k);

}
