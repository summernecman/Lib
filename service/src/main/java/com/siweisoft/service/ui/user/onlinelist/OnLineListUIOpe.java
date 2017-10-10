package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragOnlinelistBinding;
import com.siweisoft.service.databinding.ItemUserBinding;
import com.siweisoft.service.netdb.user.UserBean;

import java.util.ArrayList;

public class OnLineListUIOpe extends BaseUIOpe<FragOnlinelistBinding> {


    public OnLineListUIOpe(Context context) {
        super(context);
    }

    public void initList(final ArrayList<UserBean> data, final View.OnClickListener onClickListener) {
        for (int i = 0; data != null && i < data.size(); i++) {
            LogUtil.E(data.get(i).toString());
        }

        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_user, BR.item_user, data) {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            }

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemUserBinding viewDataBinding = (ItemUserBinding) holder.viewDataBinding;
                viewDataBinding.ivHead.setTag(R.id.data, data.get(position));
                viewDataBinding.ivHead.setOnClickListener(this);
                viewDataBinding.ivCall.setTag(R.id.data, data.get(position));
                viewDataBinding.ivCall.setOnClickListener(this);
                viewDataBinding.tvName.setText(NullUtil.isStrEmpty(data.get(position).getName()) ? data.get(position).getPhone() : StringUtil.getStr(data.get(position).getName()));
                viewDataBinding.ratingbar.setStar(data.get(position).getAvg());
                GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + data.get(position).getHeadurl()).into(viewDataBinding.ivHead);
                if (data.get(position).getState() == UserBean.STATE_OFFLINE) {
                    viewDataBinding.getRoot().setAlpha(0.3f);
                    viewDataBinding.ivHead1.setSelected(false);
                } else {
                    viewDataBinding.getRoot().setAlpha(1f);
                    viewDataBinding.ivHead1.setSelected(true);
                }

                switch (data.get(position).getUsertype()) {
                    case UserBean.CUSTOME:
                        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/usertype/icon_customer.png").into(viewDataBinding.ivHeadType);
                        break;
                    case UserBean.ENGINEER:
                        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/usertype/icon_engineer.png").into(viewDataBinding.ivHeadType);
                        break;
                    case UserBean.SERVER:
                        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/usertype/icon_server.png").into(viewDataBinding.ivHeadType);
                        break;
                }
            }
        });
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }
}
