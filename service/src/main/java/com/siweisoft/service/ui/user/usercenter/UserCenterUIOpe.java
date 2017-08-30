package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragUsercenterBinding;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class UserCenterUIOpe extends BaseUIOpe<FragUsercenterBinding> {
    public UserCenterUIOpe(Context context) {
        super(context);
    }

    public void initTips(ArrayList<String> data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, data));
    }

    public void initHead() {
        LogUtil.E(Value.userBean.getHeadurl());
        GlideApp.with(context).asBitmap().centerCrop().load(Value.userBean.getHeadurl()).into(bind.head);
        bind.setVariable(BR.frag_usercenter, Value.userBean);
    }
}
