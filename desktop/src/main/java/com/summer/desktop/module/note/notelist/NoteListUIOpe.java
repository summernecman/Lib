package com.summer.desktop.module.note.notelist;

//by summer on 2017-07-28.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
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


    public void initRefresh(final OnFinishListener onFinishListener, View.OnLongClickListener longClickListener) {
        bind.refresh.setMaterialRefreshListener(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                if (onFinishListener != null) {
                    onFinishListener.onFinish(materialRefreshLayout);
                }
            }
        });
        bind.rlRecylce.setOnLongClickListener(longClickListener);
    }

    public void fillRecycle(ArrayList<LayoutDABean> data, final View.OnLongClickListener onLongClickListener, final View.OnClickListener onClickListener) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_note_brief, BR.notebrief, data) {
            @Override
            public boolean onLongClick(View v) {
                onLongClickListener.onLongClick(v);
                return true;
            }

            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });
    }
}
