package com.summer.desktop.module.note.notelist;

//by summer on 2017-07-28.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.LayoutDABean;
import com.android.lib.ope.uiope.RecycleUIOpe;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.summer.desktop.BR;
import com.summer.desktop.R;
import com.summer.desktop.databinding.ItemRecycleBinding;

import java.util.ArrayList;

public class NoteListUIOpe extends BaseUIOpe<ItemRecycleBinding> {

    RecycleUIOpe recycleUIOpe;

    public NoteListUIOpe(Context context) {
        super(context);
        recycleUIOpe = new RecycleUIOpe(context);
    }


    public void initRefresh(final OnFinishListener onFinishListener, final ViewListener longClickListener) {
        bind.refresh.setMaterialRefreshListener(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                if (onFinishListener != null) {
                    onFinishListener.onFinish(materialRefreshLayout);
                }
            }
        });
        bind.refresh.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                longClickListener.onInterupt(ViewListener.TYPE_ONLONGCLICK, bind.recycle);
            }
        });
    }

    public void fillRecycle(ArrayList<LayoutDABean> data, ViewListener viewListener) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_note_brief, BR.notebrief, data, viewListener));

    }
}
