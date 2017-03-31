package com.summer.lib.base.interf.uiinterf;

//by summer on 2017-03-31.

import com.summer.lib.bean.uibean.BaseUIBean;

public interface IUICreate<A extends BaseUIBean> {

    public A getUibean();

    public abstract A createUI();
}
