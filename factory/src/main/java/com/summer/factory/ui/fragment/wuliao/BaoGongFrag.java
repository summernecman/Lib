package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.value.FactoryValue;

public class BaoGongFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        BaoGongFrag wuLiaoFrag = new BaoGongFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getOpes().getUi().addBangGongItem(getOpes().getDa().getData(new String[]{"流程票"}));

    }
}
