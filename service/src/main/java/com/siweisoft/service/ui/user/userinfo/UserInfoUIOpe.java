package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.RemarkBean;
import com.siweisoft.service.databinding.FragUserinfoBinding;

import java.util.ArrayList;

public class UserInfoUIOpe extends BaseUIOpe<FragUserinfoBinding> {


    public UserInfoUIOpe(Context context) {
        super(context);
    }

    public void initTips(ArrayList<String> data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, data));
    }

    public void initRemarks(ArrayList<RemarkBean> data) {
        bind.remarklist.setLayoutManager(new LinearLayoutManager(context));
        bind.remarklist.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_remark, BR.item_remark, data));
    }
}
