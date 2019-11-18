package com.anfly.summary.base;

public abstract class BaseMvpFaragment<P extends BasePresenter, V extends BaseView> extends BaseFragment {

    protected P presenter;

    @Override
    protected void initMvp() {
        super.initMvp();
        presenter = initMvpPresenter();
        if (presenter != null) {
            presenter.setView(initMvpView());
        }
    }

    protected abstract V initMvpView();

    protected abstract P initMvpPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
        }
    }
}
