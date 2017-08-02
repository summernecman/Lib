package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class WuLiao6Frag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        WuLiao6Frag wuLiaoFrag = new WuLiao6Frag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        P().U().initText2(P().D().getRecycleTitleData23());
        P().U().initRecycle();
        P().U().initBottom3(P().D().getBottomData4());
        P().U().initRecycle2(P().D().getRecycleData2());
    }
}
