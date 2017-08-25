package com.summer.factory.ui.fragment.jianhuo;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.ui.fragment.wuliao.WuLiaoFrag;
import com.summer.factory.value.FactoryValue;

public class JianHuoFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        JianHuoFrag jianHuoFrag = new JianHuoFrag();
        jianHuoFrag.setArguments(new Bundle());
        jianHuoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return jianHuoFrag;
    }

    @Override
    public void doThing() {
        getP().getU().initTitleTxtEdit(getP().getD().getTextEditData4());
        getP().getU().initText3(getP().getD().getRecycleTitleData33());
        getP().getU().initRecycle();
        getP().getU().initRecycle3(getP().getD().getRecycleData3());
    }
}
