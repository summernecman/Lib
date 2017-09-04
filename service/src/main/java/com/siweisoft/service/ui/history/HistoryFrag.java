package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

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
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.videorecord.VideoRecordFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryFrag extends BaseServerFrag<HistoryUIOpe, HistoryDAOpe> implements ViewListener {

    @Override
    public void doThing() {
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
        setTitleBean(new TitleBean("", "呼叫历史", ""));
        getP().getD().getVideos(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initList((ArrayList<ArrayList<VideoBean>>) o, HistoryFrag.this);
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                VideoRecordFrag videoRecordFrag = new VideoRecordFrag();
                videoRecordFrag.setArguments(new Bundle());
                videoRecordFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, (Serializable) v.getTag(R.id.data));
                FragmentUtil2.getInstance().add(activity, Value.ROOTID_ONE, videoRecordFrag);
                break;
        }
    }
}
