package com.siweisoft.service.ui.videorecord;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.StringUtil;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragVideorecordBinding;
import com.siweisoft.service.databinding.ItemVideorecordBinding;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;

import java.util.List;

public class VideoRecordUIOpe extends BaseUIOpe<FragVideorecordBinding> {


    public VideoRecordUIOpe(Context context) {
        super(context);
    }


    public void initList(final List<VideoBean> data, ViewListener viewListener, MyRecyclerView.OnScroll onScroll) {
        if (bind.recycle.getAdapter() == null) {
            bind.recycle.setLayoutManager(new LinearLayoutManager(context));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_videorecord, BR.item_videorecord, data, viewListener) {
                @Override
                public void onBindViewHolder(AppViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    ItemVideorecordBinding itemVideorecordBinding = (ItemVideorecordBinding) holder.viewDataBinding;
                    itemVideorecordBinding.tvUpload.setVisibility(data.get(position).getUploaded() == 1 ? View.GONE : View.VISIBLE);
                    holder.viewDataBinding.getRoot().findViewById(R.id.play).setTag(com.android.lib.R.id.data, data.get(position));
                    holder.viewDataBinding.getRoot().findViewById(R.id.iv_head).setTag(com.android.lib.R.id.data, data.get(position));
                    holder.viewDataBinding.getRoot().findViewById(R.id.iv_head).setTag(com.android.lib.R.id.position, position);
                    holder.viewDataBinding.getRoot().findViewById(R.id.iv_head).setOnClickListener(this);
                    holder.viewDataBinding.getRoot().findViewById(R.id.play).setOnClickListener(this);
                    ((ItemVideorecordBinding) holder.viewDataBinding).tvTimes.setText(StringUtil.secondToMinute(data.get(position).getTimenum()));
                    if (Value.getUserInfo().getPhone().equals(data.get(position).getFromUser().getPhone())) {
                        itemVideorecordBinding.ivWay.setSelected(false);
                        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + data.get(position).getToUser().getHeadurl()).into(itemVideorecordBinding.ivHead);
                    } else {
                        itemVideorecordBinding.ivWay.setSelected(true);
                        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + data.get(position).getFromUser().getHeadurl()).into(itemVideorecordBinding.ivHead);
                    }
                }
            });
            bind.recycle.setOnScroll(onScroll);
        } else {
            bind.recycle.getAdapter().notifyDataSetChanged();
        }

    }

    public void loadmore() {
        bind.recycle.getAdapter().notifyDataSetChanged();
    }


    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }

}
