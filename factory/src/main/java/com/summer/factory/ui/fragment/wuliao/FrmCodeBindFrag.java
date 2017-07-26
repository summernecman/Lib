package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class FrmCodeBindFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        FrmCodeBindFrag wuLiaoFrag = new FrmCodeBindFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getOpes().getUi().initTitleTxtEditTxt(getOpes().getDa().getData(new String[]{"条码", "", "清空"}));
        getOpes().getUi().initText1(getOpes().getDa().getData(new String[]{"绑定信息"}));
        getOpes().getUi().initRecycle();
        getOpes().getUi().initBottom3(getOpes().getDa().getData(new String[]{"信息"}));
        getOpes().getUi().initRecycle1(getOpes().getDa().getRecycleData2());
    }
}
