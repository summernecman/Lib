package com.siweisoft.service.ui.setting.remarklist;

//by summer on 17-08-28.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.constant.UrlConstant;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragRemarklistBinding;
import com.siweisoft.service.databinding.ItemRemarkBinding;
import com.siweisoft.service.netdb.comment.CommentBean;

import java.util.ArrayList;

public class RemarkListUIOpe extends BaseUIOpe<FragRemarklistBinding> {


    public RemarkListUIOpe(Context context) {
        super(context);
    }

    public void initRemarks(final ArrayList<CommentBean> data, MyRecyclerView.OnScroll onScroll, ViewListener listener) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_remark, BR.item_remark, data, listener) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemRemarkBinding itemRemarkBinding = (ItemRemarkBinding) holder.viewDataBinding;
                itemRemarkBinding.ivHead.setOnClickListener(this);
                itemRemarkBinding.ivHead.setTag(R.id.data, data.get(position));
                GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + data.get(position).getFromUser().getHeadurl()).into(itemRemarkBinding.ivHead);
            }
        });
        bind.recycle.setOnScroll(onScroll);
    }

    public void refreshRemarks(final ArrayList<CommentBean> data) {
        if (bind.recycle.getLayoutManager() == null || bind.recycle.getAdapter() == null) {
            return;
        }
        bind.recycle.getAdapter().notifyDataSetChanged();
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }
}
