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
        P().U().initTitleTxtEdit(P().D().getTextEditData4());
        P().U().initText3(P().D().getRecycleTitleData33());
        P().U().initRecycle();
        P().U().initRecycle3(P().D().getRecycleData3());
    }
}
