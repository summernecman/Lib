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
import com.android.lib.util.StringUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragOnlinelistBinding;
import com.siweisoft.service.databinding.ItemUserBinding;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class OnLineListUIOpe extends BaseUIOpe<FragOnlinelistBinding> {


    public OnLineListUIOpe(Context context) {
        super(context);
    }

    public void initList(final ArrayList<UserBean> data, final View.OnClickListener onClickListener) {
        for (int i = 0; i < data.size(); i++) {
            LogUtil.E(data.get(i).toString());
        }
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_user, BR.item_user, data) {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                onClickListener.onClick(v);
            }

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemUserBinding viewDataBinding = (ItemUserBinding) holder.viewDataBinding;
                viewDataBinding.ivHead.setTag(R.id.data, data.get(position));
                viewDataBinding.ivHead.setOnClickListener(this);
                viewDataBinding.ivCall.setTag(R.id.data, data.get(position));
                viewDataBinding.ivCall.setOnClickListener(this);
                viewDataBinding.tvName.setText(StringUtil.getStr(data.get(position).getName()));
                GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/" + data.get(position).getHeadurl()).into(viewDataBinding.ivHead);
                LogUtil.E("111111111111:" + UrlConstant.fileUrl + "/" + data.get(position).getHeadurl());
            }
        });
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }
}
