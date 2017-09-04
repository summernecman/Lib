package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.BR;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.databinding.FragUserinfoBinding;
import com.siweisoft.service.netdb.comment.CommentBean;
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
                @Override
                public void onClick(View v) {
                    super.onClick(v);
                    tipsBean.getTipBeen().get((Integer) v.getTag(R.id.position)).setSelect(tipsBean.getTipBeen().get((Integer) v.getTag(R.id.position)).isSelect());
                }
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
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, tipBeen));
    }

    public void initRemarks(ArrayList<CommentBean> data) {
        bind.remarklist.setLayoutManager(new LinearLayoutManager(context));
        bind.remarklist.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_remark, BR.item_remark, data));
    }

    public void initCallInfo(VideoTimeBean videoTimeBean) {
        bind.setCallinfo1(videoTimeBean);
    }

}
