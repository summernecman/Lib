package com.siweisoft.service.ui.videorecord;

//by summer on 17-08-23.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.view.ItemDecoration.MyItemDecoration;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragVideorecordBinding;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.List;

public class VideoRecordUIOpe extends BaseUIOpe<FragVideorecordBinding> {


    public VideoRecordUIOpe(Context context) {
        super(context);
    }


    public void initList(final List<VideoBean> data, ViewListener viewListener) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.addItemDecoration(new MyItemDecoration(context, 1, Color.GRAY));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_videorecord, BR.item_videorecord, data, viewListener) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.viewDataBinding.getRoot().findViewById(R.id.play).setTag(com.android.lib.R.id.data, data.get(position));
                holder.viewDataBinding.getRoot().findViewById(R.id.play).setOnClickListener(this);
            }
        });
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }

}
