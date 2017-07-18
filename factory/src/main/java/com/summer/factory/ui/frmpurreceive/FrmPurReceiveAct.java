package com.summer.factory.ui.frmpurreceive;

//by summer on 2017-07-17.

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.summer.factory.ui.base.BaseFactoryAct;
import com.summer.factory.ui.fragment.tiaoma.TiaoMaFrag;
import com.summer.factory.ui.fragment.wuliao.WuLiaoFrag;

import java.util.ArrayList;

public class FrmPurReceiveAct extends BaseFactoryAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public ArrayList<Fragment> initFrag() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(WuLiaoFrag.getInstance(new Bundle()));
        fragments.add(TiaoMaFrag.getInstance(new Bundle()));
        return fragments;
    }

}
