package com.siweisoft.service.ui.user.userlist;

//by summer on 17-09-06.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragUserlistBinding;
import com.siweisoft.service.databinding.ItemUser2Binding;
import com.siweisoft.service.netdb.user.UserBean;

import java.util.ArrayList;

public class UserListUIOpe extends BaseUIOpe<FragUserlistBinding> {


    public UserListUIOpe(Context context) {
        super(context);
    }


    public void initList(final ArrayList<UserBean> data, final ViewListener viewListener) {
        for (int i = 0; i < data.size(); i++) {
            LogUtil.E(data.get(i).toString());
        }
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_user2, BR.item_user2, data, viewListener) {

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemUser2Binding viewDataBinding = (ItemUser2Binding) holder.viewDataBinding;
                viewDataBinding.ivHead.setTag(R.id.data, data.get(position));
                viewDataBinding.ivHead.setOnClickListener(this);
                GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + data.get(position).getHeadurl()).into(viewDataBinding.ivHead);
                LogUtil.E("111111111111:" + UrlConstant.fileUrl + "/" + data.get(position).getHeadurl());
            }
        });
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }
}
