package com.siweisoft.service.ui.dialog.list;

//by summer on 17-09-11.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragDialoglistBinding;

import java.util.List;

public class DialogListUIOpe extends BaseUIOpe<FragDialoglistBinding> {


    public DialogListUIOpe(Context context) {
        super(context);
    }

    public void initList(List l, ViewListener listener) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 3));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_text, BR.item_text, l, listener));
    }

}
