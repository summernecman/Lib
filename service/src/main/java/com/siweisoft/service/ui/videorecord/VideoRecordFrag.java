package com.siweisoft.service.ui.videorecord;

//by summer on 17-08-23.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.OnClick;

public class VideoRecordFrag extends BaseUIFrag<VideoRecordUIOpe, VideoRecordDAOpe> implements ViewListener {

    @Override
    public void doThing() {
        getP().getU().initTitle();
        getP().getD().setVideos((ArrayList<VideoBean>) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initList(getP().getD().getVideos(), VideoRecordFrag.this);

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

    @OnClick({R.id.ftv_back, R.id.ftv_title, R.id.ftv_right})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.ftv_title:

                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                break;
        }
    }
}
