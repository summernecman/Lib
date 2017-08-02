package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class WuLiao2Frag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        WuLiao2Frag wuLiaoFrag = new WuLiao2Frag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        P().U().initTitleTxtEdit(P().D().getTextEditData2());
        P().U().initText2(P().D().getRecycleTitleData2());
        P().U().initRecycle();
        P().U().initBottom(P().D().getBottomData1());
        P().U().initRecycle2(P().D().getRecycleData2());
    }
}
