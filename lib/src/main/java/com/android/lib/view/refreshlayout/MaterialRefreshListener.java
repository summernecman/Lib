package com.android.lib.view.refreshlayout;


public interface MaterialRefreshListener {
    void onfinish();

    void onRefresh(MaterialRefreshLayout materialRefreshLayout);

    void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout);
}
