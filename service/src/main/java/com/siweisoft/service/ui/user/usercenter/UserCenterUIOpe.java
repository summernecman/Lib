package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.android.lib.base.activity.BaseActivity;
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
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.databinding.FragUsercenterBinding;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoTimeBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

import java.util.ArrayList;

public class UserCenterUIOpe extends BaseUIOpe<FragUsercenterBinding> {
    public UserCenterUIOpe(Context context) {
        super(context);
    }

    public void initTips(TipsBean data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip4, BR.item_tip4, data.getTipBeen()));
    }

    public void initTips(ArrayList<TipBean> tipBeen) {
        if (tipBeen == null || tipBeen.size() == 0) {
            UserInfoDAOpe userInfoDAOpe = new UserInfoDAOpe(context);
            final TipsBean tipsBean = userInfoDAOpe.getData();
            bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip2, BR.item_tip2, tipsBean.getTipBeen()) {
            });
            return;
        }
        if (bind.recycle.getAdapter() == null) {
            bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, tipBeen) {
                @Override
                public void onBindViewHolder(AppViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    holder.viewDataBinding.getRoot().setSelected(true);
                }
            });
        } else {
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

    public void initCallInfo(VideoTimeBean videoTimeBean) {
        bind.setCallinfo(videoTimeBean);
    }

    public void initHead() {
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            if (activity.isDestroyed()) {
                return;
            }
        }
        LogUtil.E(Value.getUserInfo().getHeadurl());
        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + Value.getUserInfo().getHeadurl()).into(bind.head);
        bind.setVariable(BR.frag_usercenter, Value.getUserInfo());
        if (Value.getUserInfo() == null || Value.getUserInfo().getUsertype() == null) {
            return;
        }
        switch (Value.getUserInfo().getUsertype()) {
            case UserBean.CUSTOME:
                GlideApp.with(context).asDrawable().load(R.drawable.icon_customer).into(bind.type);
                break;
            case UserBean.ENGINEER:
                GlideApp.with(context).asDrawable().load(R.drawable.icon_engineer).into(bind.type);
                break;
            case UserBean.SERVER:
                GlideApp.with(context).asDrawable().load(R.drawable.icon_server).into(bind.type);
                break;
        }

        bind.tvUnit.setText(StringUtil.getStr(Value.getUserInfo().getUnit().getUnitname()));
        if (Value.getUserInfo().getUsertype() != UserBean.USER_TYPE_CUSTOMER) {
            bind.llUnit.setVisibility(View.GONE);
        }
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }

    public void initCommentNum(String num) {
        bind.tvCommentnum.setText(num);
    }

    public void initCollectNum(String num) {
        bind.tvCollectnum.setText(num);
    }


    public void initShareNum(String num) {
        bind.tvShare.setText(num);
    }

    public void initUnit() {

    }
}
