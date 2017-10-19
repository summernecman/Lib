package com.siweisoft.service.ui.setting.collect;

//by summer on 17-08-28.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListenerAdpter;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class CollecFrag extends BaseServerFrag<CollectUIOpe, CollectDAOpe> implements ViewListener {

    @Override
    public void doThing() {
        super.doThing();
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
    public void initData() {
        super.initData();
        setTitleBean(new TitleBean("返回", "收藏", ""));
        getP().getD().setPagestart(0);
        getP().getD().setPagesize(5);
        Value.getUserInfo().setPagesize(getP().getD().getPagesize());
        Value.getUserInfo().setPagestart(getP().getD().getPagestart());
        getP().getD().getCollection(Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getVideos().clear();
                getP().getD().getVideos().addAll((ArrayList<VideoBean>) o);
                getP().getU().initList(getP().getD().getVideos(), CollecFrag.this, new MyRecyclerView.OnScroll() {
                    @Override
                    public void onScrollToEnd(MyRecyclerView myRecyclerView) {
                        initData2();
                    }
                });
                getP().getD().setPagestart(getP().getD().getPagestart() + 1);
                getP().getU().bind.refresh.finishRefreshingDelay();
            }
        });
    }


    public void initData2() {
        Value.getUserInfo().setPagesize(getP().getD().getPagesize());
        Value.getUserInfo().setPagestart(getP().getD().getPagestart());
        getP().getD().getCollection(Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getVideos().addAll((ArrayList<VideoBean>) o);
                getP().getU().loadMore();
                getP().getD().setPagestart(getP().getD().getPagestart() + 1);
                getP().getU().bind.refresh.finishRefreshLoadMore();
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
                    case R.id.iv_head:
                        UserInfoFrag userInfoFrag = new UserInfoFrag();
                        userInfoFrag.setArguments(new Bundle());
                        userInfoFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getVideos().get((Integer) v.getTag(R.id.position)).getOtherUser());
                        FragmentUtil2.getInstance().add(activity, Value.ROOTID_THREE, userInfoFrag);
                        break;
                }
                break;
        }
    }
}
