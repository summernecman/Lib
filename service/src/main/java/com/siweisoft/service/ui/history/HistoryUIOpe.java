package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragHistoryBinding;
import com.siweisoft.service.databinding.ItemHistoryBinding;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class HistoryUIOpe extends BaseUIOpe<FragHistoryBinding> {

    public HistoryUIOpe(Context context) {
        super(context);
    }

    public void initList(final ArrayList<ArrayList<VideoBean>> data, ViewListener listener) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_history, BR.item_history, data, listener) {
                @Override
                public void onBindViewHolder(AppViewHolder holder, int position) {
                    ItemHistoryBinding viewDataBinding = (ItemHistoryBinding) holder.viewDataBinding;
                    viewDataBinding.getRoot().setTag(com.android.lib.R.id.data, list.get(position));
                    viewDataBinding.getRoot().setTag(com.android.lib.R.id.position, position);
                    viewDataBinding.getRoot().setOnClickListener(this);
                    viewDataBinding.getRoot().setOnLongClickListener(this);
                    viewDataBinding.setVariable(vari, data.get(position).get(0));
                    viewDataBinding.tvVideonum.setText(StringUtil.getStr(data.get(position).size()));
                    if (Value.userBean.getPhone().equals(data.get(position).get(0).getFromUser().getPhone())) {
                        GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/" + data.get(position).get(0).getToUser().getHeadurl()).into(viewDataBinding.ivHead);
                        if (NullUtil.isStrEmpty(data.get(position).get(0).getToUser().getName())) {
                            viewDataBinding.tvTophone.setText(data.get(position).get(0).getToUser().getPhone());
                        } else {
                            viewDataBinding.tvTophone.setText(data.get(position).get(0).getToUser().getName());
                        }
                    } else {
                        GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/" + data.get(position).get(0).getFromUser().getHeadurl()).into(viewDataBinding.ivHead);
                        if (NullUtil.isStrEmpty(data.get(position).get(0).getFromUser().getName())) {
                            viewDataBinding.tvTophone.setText(data.get(position).get(0).getFromUser().getPhone());
                        } else {
                            viewDataBinding.tvTophone.setText(data.get(position).get(0).getFromUser().getName());
                        }
                    }
                    viewDataBinding.executePendingBindings();//加一行，问题解决
//                    viewDataBinding.tvTophone.setText(data.get(position).get(0).getTophone());
//                    viewDataBinding.tvDate.setText(data.get(position).get(0).getCreated());
                }
            });
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }
}
