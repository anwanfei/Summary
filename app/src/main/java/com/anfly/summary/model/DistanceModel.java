package com.anfly.summary.model;

import com.anfly.summary.callback.DistanceCallback;

public interface DistanceModel {
    void getFeed(int page, DistanceCallback distanceCallback);
}
