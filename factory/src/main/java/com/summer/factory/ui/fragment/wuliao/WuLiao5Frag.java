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
        getOpes().getUi().initText4(getOpes().getDa().getRecycleTitleData32());
        getOpes().getUi().initRecycle();
        getOpes().getUi().initBottom2(getOpes().getDa().getBottomData2());
        getOpes().getUi().initRecycle4(getOpes().getDa().getRecycleData4());
    }
}
