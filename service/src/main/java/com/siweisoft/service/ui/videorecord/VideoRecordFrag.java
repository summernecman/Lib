package com.siweisoft.service.ui.videorecord;

//by summer on 17-08-23.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.ContactBean;
import com.siweisoft.service.bean.HistoryBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoRecordFrag extends BaseServerFrag<VideoRecordUIOpe, VideoRecordDAOpe> implements ViewListener {

    @Override
    public void initData() {
        super.initData();
        setTitleBean(new TitleBean("返回", "录像", ""));
        getP().getD().setHistoryBean((HistoryBean) getArguments().getSerializable(Value.DATA_DATA));
        ContactBean contactBean = new ContactBean();
        contactBean.setFromid(Value.userBean.getId());
        contactBean.setPagesize(5);
        getP().getD().setPageindex(0);
        contactBean.setPagestart(getP().getD().getPageindex());
        contactBean.setToid(getP().getD().getHistoryBean().getUserBean().getId());
        getP().getD().getVideosByBothUserIdWithLimit(contactBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().setVideos((ArrayList<VideoBean>) o);
                getP().getU().initList(getP().getD().getVideos(), VideoRecordFrag.this);
                getP().getU().bind.refresh.finishRefresh();
                getP().getD().setPageindex(getP().getD().getPageindex() + 1);
            }
        });

    }


    public void initData2() {
        super.initData();
        ContactBean contactBean = new ContactBean();
        contactBean.setFromid(Value.userBean.getId());
        contactBean.setPagesize(5);
        contactBean.setPagestart(getP().getD().getPageindex());
        contactBean.setToid(getP().getD().getHistoryBean().getUserBean().getId());
        getP().getD().getVideosByBothUserIdWithLimit(contactBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<VideoBean> a = (ArrayList<VideoBean>) o;
                if (a == null || a.size() == 0) {
                    ToastUtil.getInstance().showShort(activity, "加载完毕");
                }
                getP().getD().getVideos().addAll((ArrayList<VideoBean>) o);
                getP().getU().loadmore();
                getP().getD().setPageindex(getP().getD().getPageindex() + 1);
                getP().getU().bind.refresh.finishRefreshLoadMore();
            }
        });

    }

    @Override
    public void doThing() {
        getP().getU().initRefresh(new MaterialRefreshListenerAdpter() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                initData2();
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
                        FragmentUtil2.getInstance().add(activity, Value.ROOTID_ONE, playFrag);
                        break;
                    case R.id.iv_head:
                        UserInfoFrag userInfoFrag = new UserInfoFrag();
                        userInfoFrag.setArguments(new Bundle());
                        userInfoFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getVideos().get((Integer) v.getTag(R.id.position)).getOtherUser());
                        FragmentUtil2.getInstance().add(activity, Value.ROOTID_ONE, userInfoFrag);
                        break;
                }
                break;
        }
    }
}
