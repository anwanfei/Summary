package com.anfly.summary.model;


import com.anfly.summary.api.HomeService;
import com.anfly.summary.base.BaseModel;
import com.anfly.summary.base.BaseObserver;
import com.anfly.summary.bean.BannerBean;
import com.anfly.summary.bean.HomeComposeBean;
import com.anfly.summary.bean.HomeListBean;
import com.anfly.summary.callback.HomeCallback;
import com.anfly.summary.utils.HttpUtil;
import com.anfly.summary.utils.RxUtil;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class ImpHomeModel extends BaseModel implements HomeModel {
    @Override
    public void getHomeUrl(int page, HomeCallback homeCallback) {
        HomeService server = HttpUtil.getHttpUtil().getServer(HomeService.baseUrl, HomeService.class);
//        Observable<HomeBean> observable = server.getHomeUrl();
//        observable.flatMap(new Function<HomeBean, ObservableSource<HomeComposeBean>>() {
//            @Override
//            public ObservableSource<HomeComposeBean> apply(HomeBean homeBean) throws Exception {
//                String articleUrl = homeBean.getArticle();
//                String bannerUrl = homeBean.getBanner();
//                Observable<List<BannerBean>> bannerBeanObservable = server.getHomeBanner();
//                Observable<HomeListBean> homeListBeanObservable = server.getHomeList();
//
//                Observable<HomeComposeBean> zip = Observable.zip(bannerBeanObservable, homeListBeanObservable, new BiFunction<List<BannerBean>, HomeListBean, HomeComposeBean>() {
//                    @Override
//                    public HomeComposeBean apply(List<BannerBean> bannerBeans, HomeListBean homeListBean) throws Exception {
//                        return new HomeComposeBean(bannerBeans, homeListBean.getData().getDatas());
//                    }
//                });
//                return zip;
//            }
//        }).compose(RxUtil.rxObservableTransformer())
//                .subscribe(new BaseObserver<HomeComposeBean>(this) {
//                    @Override
//                    protected void onSuccess(HomeComposeBean homeComposeBean) {
//                        homeCallback.onSuccess(homeComposeBean);
//                    }
//
//                    @Override
//                    protected void onFail(String error) {
//                        homeCallback.onFail(error);
//                    }
//                });

        Observable<BannerBean> bannerBeanObservable = server.getHomeBanner();
        Observable<HomeListBean> homeListBeanObservable = server.getHomeList(page);

        Observable.zip(bannerBeanObservable, homeListBeanObservable, new BiFunction<BannerBean, HomeListBean, HomeComposeBean>() {
            @Override
            public HomeComposeBean apply(BannerBean bannerBean, HomeListBean homeListBean) throws Exception {
                return new HomeComposeBean(bannerBean.getData(), homeListBean.getData().getDatas());
            }
        }).compose(RxUtil.rxObservableTransformer())
                .subscribe(new BaseObserver<HomeComposeBean>(this) {
                    @Override
                    protected void onSuccess(HomeComposeBean homeComposeBean) {
                        homeCallback.onSuccess(homeComposeBean);
                    }

                    @Override
                    protected void onFail(String error) {
                        homeCallback.onFail(error);
                    }
                });

    }
}
