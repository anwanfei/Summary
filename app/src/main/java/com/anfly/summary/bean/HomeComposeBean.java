package com.anfly.summary.bean;

import java.util.List;
import java.util.Locale;

/**
 * 合并BannerBean & ListBean
 */
public class HomeComposeBean {

    public List<BannerBean.DataBean> banners;

    public List<HomeListBean.DataBean.DatasBean> results;

    public HomeComposeBean(List<BannerBean.DataBean> banners, List<HomeListBean.DataBean.DatasBean> results) {
        this.banners = banners;
        this.results = results;
    }

    @Override
    public String toString() {
        return String.format(Locale.CHINESE,
                "%s中包含 %d 张轮播图，%d 个列表条目",
                this.getClass().getSimpleName(), banners.size(), results.size());
    }
}
