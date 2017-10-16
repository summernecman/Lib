package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
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
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.databinding.FragUserinfoBinding;
import com.siweisoft.service.databinding.ItemRemarkBinding;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoTimeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UserInfoUIOpe extends BaseUIOpe<FragUserinfoBinding> {



    public UserInfoUIOpe(Context context) {
        super(context);

    }

    public void initTips(final HashMap<Integer, TipBean> data) {
        if (data == null || data.keySet() == null || data.keySet().size() == 0) {
            UserInfoDAOpe userInfoDAOpe = new UserInfoDAOpe(context);
            final TipsBean tipsBean = userInfoDAOpe.getData();
            bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, tipsBean.getTipBeen()) {
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

    public void initRemarks(final ArrayList<CommentBean> data, ViewListener viewListener) {
        bind.remarklist.setLayoutManager(new LinearLayoutManager(context));
        bind.remarklist.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_remark, BR.item_remark, data, viewListener) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemRemarkBinding itemRemarkBinding = (ItemRemarkBinding) holder.viewDataBinding;
                itemRemarkBinding.ivAgree.setOnClickListener(this);
                itemRemarkBinding.ivAgree.setTag(R.id.data, data.get(position));
                itemRemarkBinding.ivAgree.setTag(R.id.data1, itemRemarkBinding.tvNum);
                itemRemarkBinding.ivAgree.setSelected(data.get(position).isAgree());
                GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + data.get(position).getFromUser().getHeadurl()).into(itemRemarkBinding.ivHead);
            }
        });
    }

    public void refreshRemarks() {
        if (bind.remarklist.getLayoutManager() == null || bind.remarklist.getAdapter() == null) {
            return;
        }
        bind.remarklist.getAdapter().notifyDataSetChanged();
    }


    public void initCallInfo(VideoTimeBean videoTimeBean) {
        bind.setCallinfo1(videoTimeBean);
    }

    public void initHead(UserBean userBean) {
        bind.ratingbar.setStar(userBean.getRate());
        bind.tvName.setText(NullUtil.isStrEmpty(userBean.getName()) ? userBean.getPhone() : StringUtil.getStr(userBean.getName()));
        bind.tvPhone.setText(userBean.getPhone());
        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(UrlConstant.fileUrl + "/" + userBean.getHeadurl()).into(bind.ivHead11);
    }


    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }

    public void initOnline(boolean online) {
        bind.call.setVisibility(online ? View.VISIBLE : View.GONE);
    }
}
