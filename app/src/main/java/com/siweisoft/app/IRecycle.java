package com.siweisoft.app;

//by summer on 2017-03-31.

import com.summer.lib.base.adapter.AppRecycleAdapter;

public interface IRecycle {

    public void init();

    public void load(AppRecycleAdapter appRecycleAdapter);

    public void pull();

    public void push();

}
