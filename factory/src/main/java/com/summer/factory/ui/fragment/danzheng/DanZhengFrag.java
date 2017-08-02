package com.summer.factory.ui.fragment.danzheng;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.factory.value.FactoryValue;

public class DanZhengFrag extends BaseUIFrag<DanZhengUIOpe, DanZhengDAOpe> {


    public static Fragment getInstance(String title) {
        DanZhengFrag danZhengFrag = new DanZhengFrag();
        danZhengFrag.setArguments(new Bundle());
        danZhengFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return danZhengFrag;
    }


    @Override
    public void doThing() {
        P().U().initData(P().D().getDanzhengData());
    }
}
