package com.summer.factory.ui.fragment.wuliao;

//by summer on 2017-07-18.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.bean.da.LayoutDABean;
import com.summer.factory.value.FactoryValue;

import java.util.ArrayList;

public class FrmConFirmSoFrag extends WuLiaoFrag {

    public static Fragment getInstance(String title) {
        FrmConFirmSoFrag wuLiaoFrag = new FrmConFirmSoFrag();
        wuLiaoFrag.setArguments(new Bundle());
        wuLiaoFrag.getArguments().putString(FactoryValue.FRAG_TITLE, title);
        return wuLiaoFrag;
    }

    @Override
    public void doThing() {
        getP().getU().initTitleTxtEdit(getP().getD().getData(new String[]{"销货单", ""}));
        getP().getU().initTitleTxt(getP().getD().getData(new String[]{"大、小包装/合格证"}));
        getP().getU().initText3(getP().getD().getData(new String[]{"品号", "单据数量", "拣货"}));
        getP().getU().initRecycle();
        ArrayList<LayoutDABean> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            data.add(getP().getD().getData(new String[]{"品号", "单据数量", "拣货"}));
        }
        getP().getU().initRecycle3(data);


    }
}
