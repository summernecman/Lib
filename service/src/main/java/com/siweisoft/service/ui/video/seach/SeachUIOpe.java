package com.siweisoft.service.ui.video.seach;

//by summer on 17-09-11.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragSeachBinding;
import com.siweisoft.service.netdb.videotip.VideoTipBean;

import java.util.ArrayList;

public class SeachUIOpe extends BaseUIOpe<FragSeachBinding> {


    public SeachUIOpe(Context context) {
        super(context);
    }

    public void initList(final ArrayList<VideoTipBean> data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 3));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_text, BR.item_text, data) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.viewDataBinding.getRoot().setSelected(data.get(position).isSelect());
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                data.get((Integer) v.getTag(R.id.position)).setSelect(!data.get((Integer) v.getTag(R.id.position)).isSelect());
                v.setSelected(data.get((Integer) v.getTag(R.id.position)).isSelect());
            }
        });
    }

}
