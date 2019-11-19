package com.anfly.summary.model;

import com.anfly.summary.callback.HomeCallback;

public interface HomeModel {
    void getHomeUrl(int page,HomeCallback homeCallback);
}
