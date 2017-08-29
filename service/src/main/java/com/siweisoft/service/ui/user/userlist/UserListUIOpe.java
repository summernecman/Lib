package com.siweisoft.service.ui.user.userlist;

//by summer on 2017-07-04.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragUserlistBinding;
import com.siweisoft.service.databinding.ItemUserBinding;
import com.siweisoft.service.ui.user.login.UserBean;

import java.util.ArrayList;

public class UserListUIOpe extends BaseUIOpe<FragUserlistBinding> {


    public UserListUIOpe(Context context) {
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
            }
        });
    }
}
