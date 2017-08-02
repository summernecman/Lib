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
        P().U().initText4(P().D().getRecycleTitleData32());
        P().U().initRecycle();
        P().U().initBottom2(P().D().getBottomData2());
        P().U().initRecycle4(P().D().getRecycleData4());
    }
}
