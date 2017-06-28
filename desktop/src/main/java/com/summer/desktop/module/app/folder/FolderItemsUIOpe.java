package com.summer.desktop.module.app.folder;

//by summer on 2017-06-28.

import android.content.Context;

import com.android.lib.base.ope.BaseUIBean;
import com.summer.desktop.bean.dbbean.AppDBBean;
import com.summer.desktop.databinding.NormalBinding;

import java.util.ArrayList;

public class FolderItemsUIOpe extends BaseUIBean<NormalBinding> {

    public FolderItemsUIOpe(Context context) {
        super(context);
    }

    public void init(ArrayList<ArrayList<AppDBBean>> lists) {
        viewDataBinding.classifyView.setAdapter(new MyAdapter(lists));
    }
}
