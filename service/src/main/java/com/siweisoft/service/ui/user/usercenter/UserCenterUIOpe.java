package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
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
import java.util.HashMap;
import java.util.Iterator;

public class UserCenterUIOpe extends BaseUIOpe<FragUsercenterBinding> {
    public UserCenterUIOpe(Context context) {
        super(context);
    }

    public void initTips(TipsBean data) {
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, data.getTipBeen()));
    }

    public void initTips(HashMap<Integer, TipBean> data) {
        if (data == null || data.keySet() == null || data.keySet().size() == 0) {
            UserInfoDAOpe userInfoDAOpe = new UserInfoDAOpe(context);
            final TipsBean tipsBean = userInfoDAOpe.getData();
            bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip2, BR.item_tip2, tipsBean.getTipBeen()) {
            });
            return;
        }
        ArrayList<TipBean> tipBeen = new ArrayList<>();
        Iterator<Integer> iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            LogUtil.E("key:" + key + ":" + data.get(key).getTip() + ":" + data.get(key).getNum());
            tipBeen.add(new TipBean(key, data.get(key).getTip(), data.get(key).getNum()));
        }
        bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, tipBeen) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.viewDataBinding.getRoot().setSelected(true);
            }
        });
    }

    public void initCallInfo(VideoTimeBean videoTimeBean) {
        bind.setCallinfo(videoTimeBean);
    }

    public void initHead() {
        LogUtil.E(Value.userBean.getHeadurl());
        GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/" + Value.userBean.getHeadurl()).into(bind.head);
        bind.setVariable(BR.frag_usercenter, Value.userBean);
        switch (Value.userBean.getUsertype()) {
            case UserBean.CUSTOME:
                GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/usertype/icon_customer").into(bind.type);
                break;
            case UserBean.ENGINEER:
                GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/usertype/icon_engineer" + Value.userBean.getHeadurl()).into(bind.type);
                break;
            case UserBean.SERVER:
                GlideApp.with(context).asBitmap().centerCrop().load(UrlConstant.fileUrl + "/usertype/icon_server" + Value.userBean.getHeadurl()).into(bind.type);
                break;
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
