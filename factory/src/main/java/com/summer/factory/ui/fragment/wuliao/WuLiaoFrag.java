package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.fragment.BaseUIFrag;
import com.summer.factory.value.FactoryValue;

public class WuLiaoFrag extends BaseUIFrag<WuLiaoUIOpe, WuLiaoDAOpe> {

    public static Fragment getInstance(String title) {
        WuLiaoFrag wuLiaoFrag = new WuLiaoFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, "物料");
        return wuLiaoFrag;
    }


    @Override
    public void doThing() {
        getOpes().getUi().initTitleTxtEdit(getOpes().getDa().getTextEditData());
        getOpes().getUi().initText3(getOpes().getDa().getRecycleTitleData3());
        getOpes().getUi().initRecycle();
        getOpes().getUi().initBottom(getOpes().getDa().getBottomData1());
        getOpes().getUi().initRecycle3(getOpes().getDa().getRecycleData3());
    }

}
