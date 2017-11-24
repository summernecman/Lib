package com.summer.time.ui.main;

//by summer on 2017-11-21.

import android.content.Context;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.time.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainUIOpe extends BaseUIOpe<ActMainBinding> {



    public MainUIOpe(Context context) {
        super(context);
    }

    public void initTime(final ArrayList<BaseUIFrag> fragments) {
        bind.vpVp.init(fragments);
    }


}
