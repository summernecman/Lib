package com.siweisoft.service.ui.videorecord;

//by summer on 17-08-23.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

import java.io.Serializable;
import java.util.List;

public class VideoRecordFrag extends BaseUIFrag<VideoRecordUIOpe, VideoRecordDAOpe> implements ViewListener {

    @Override
    public void doThing() {
        getP().getD().getHistory(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                List<VideoBean> retList = (List<VideoBean>) o;
                getP().getU().initList(retList, VideoRecordFrag.this);
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
                    default:
                        FragmentUtil2.getInstance().add(activity, Value.ROOTID_ONE, new UserInfoFrag());
                        break;
                }
                break;
        }
    }
}
