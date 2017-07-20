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
        getOpes().getUi().initText2(getOpes().getDa().getRecycleTitleData22());
        getOpes().getUi().initRecycle();
        getOpes().getUi().initBottom3(getOpes().getDa().getBottomData3());
        getOpes().getUi().initRecycle2(getOpes().getDa().getRecycleData2());
    }
}
