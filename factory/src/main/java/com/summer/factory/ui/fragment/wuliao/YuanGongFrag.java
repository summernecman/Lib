package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class YuanGongFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        YuanGongFrag wuLiaoFrag = new YuanGongFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getP().getU().initText2(getP().getD().getRecycleTitleData22());
        getP().getU().initRecycle();
        getP().getU().initBottom3(getP().getD().getBottomData3());
        getP().getU().initRecycle2(getP().getD().getRecycleData2());
    }
}
