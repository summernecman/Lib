package com.siweisoft.app.impl;

//by summer on 2017-03-31.

import com.android.lib.base.adapter.AppRecycleAdapter;

public interface IRecycle {

    public void init();

    public void load(AppRecycleAdapter appRecycleAdapter);

    public void pull();

    public void push();

}
