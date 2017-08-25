package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.view.ItemDecoration.MyItemDecoration;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragHistoryBinding;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.ArrayList;

public class HistoryUIOpe extends BaseUIOpe<FragHistoryBinding> {

    public HistoryUIOpe(Context context) {
        super(context);
    }

    public void initList(ArrayList<VideoBean> data, ViewListener listener) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.addItemDecoration(new MyItemDecoration(context, 3, Color.BLUE));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_history, BR.item_history, data, listener));

    }
}
