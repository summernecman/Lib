package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.databinding.FragUserinfoBinding;
import com.siweisoft.service.netdb.comment.CommentBean;

import java.util.ArrayList;

public class UserInfoUIOpe extends BaseUIOpe<FragUserinfoBinding> {



    public UserInfoUIOpe(Context context) {
        super(context);

    }

    public void initTips(final TipsBean data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, data.getTipBeen()) {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                data.getTipBeen().get((Integer) v.getTag(R.id.position)).setSelect(data.getTipBeen().get((Integer) v.getTag(R.id.position)).isSelect());
            }
        });
    }

    public void initRemarks(ArrayList<CommentBean> data) {
        bind.remarklist.setLayoutManager(new LinearLayoutManager(context));
        bind.remarklist.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_remark, BR.item_remark, data));
    }

}
