package com.summer.lib.view.refreshlayout2;


public abstract class MaterialRefreshListener {
    public void onfinish() {
    }

    public abstract void onRefresh(MaterialRefreshLayout materialRefreshLayout);

    public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
    }

}
