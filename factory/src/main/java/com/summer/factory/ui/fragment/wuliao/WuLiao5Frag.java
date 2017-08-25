package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class WuLiao5Frag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        WuLiao5Frag wuLiaoFrag = new WuLiao5Frag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getP().getU().initText4(getP().getD().getRecycleTitleData32());
        getP().getU().initRecycle();
        getP().getU().initBottom2(getP().getD().getBottomData2());
        getP().getU().initRecycle4(getP().getD().getRecycleData4());
    }
}
