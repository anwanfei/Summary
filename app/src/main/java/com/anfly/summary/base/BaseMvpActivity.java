package com.anfly.summary.base;

public abstract class BaseMvpActivity<P extends BasePresenter, V extends BaseView> extends BaseActivity {

    protected P presenter;

    @Override
    protected void initMvp() {
        super.initMvp();
        presenter = initPresenter();

        if (presenter != null) {
            presenter.setView(initMvpView());
        }
    }

    protected abstract V initMvpView();

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
        }
    }
}
