package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class WuLiao4Frag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        WuLiao4Frag wuLiaoFrag = new WuLiao4Frag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        P().U().initTitleTxtSpiCheckTxt(P().D().getTextEditData3());
        P().U().initText3(P().D().getRecycleTitleData32());
        P().U().initRecycle();
        P().U().initBottom(P().D().getBottomData1());
        P().U().initRecycle3(P().D().getRecycleData3());
    }
}
