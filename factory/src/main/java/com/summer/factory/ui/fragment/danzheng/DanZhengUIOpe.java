package com.summer.factory.ui.fragment.danzheng;

//by summer on 2017-07-20.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.databinding.FragDanzhengBinding;

public class DanZhengUIOpe extends BaseUIOpe<FragDanzhengBinding> {


    public DanZhengUIOpe(Context context) {
        super(context);
    }

    public void initData(LayoutDABean bean) {
        bind.setDanzheng(bean);
    }
}
