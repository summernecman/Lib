package com.siweisoft.service.ui.setting.sharelist;

//by summer on 17-08-28.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.share.ShareBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class ShareListFrag extends BaseServerFrag<ShareListUIOpe, ShareListDAOpe> implements ViewListener {

    @Override
    public void doThing() {
        super.doThing();
        getP().getU().initRefresh(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();
                materialRefreshLayout.finishRefreshingDelay();
            }
        });

    }

    @Override
    public void initData() {
        super.initData();
        setTitleBean(new TitleBean("返回", "分享", ""));
        ShareBean shareBean = new ShareBean();
        shareBean.setReceiptid(Value.getUserInfo().getId());
        getP().getD().getSharesByReceipt(shareBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().setVideos((ArrayList<VideoBean>) o);
                getP().getU().initList(getP().getD().getVideos(), ShareListFrag.this);
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()) {
                    case R.id.play:
                        VideoPlayFrag playFrag = new VideoPlayFrag();
                        playFrag.setArguments(new Bundle());
                        playFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, (Serializable) v.getTag(R.id.data));
                        FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, playFrag);
                        break;
                    default:
                        UserInfoFrag userInfoFrag = new UserInfoFrag();
                        userInfoFrag.setArguments(new Bundle());
                        UserBean userBean = new UserBean();
                        userBean.setPhone(getP().getD().getVideos().get((Integer) v.getTag(R.id.position)).getOthername());
                        userInfoFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, userBean);
                        FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, userInfoFrag);
                        break;
                }
                break;
        }
    }
}
