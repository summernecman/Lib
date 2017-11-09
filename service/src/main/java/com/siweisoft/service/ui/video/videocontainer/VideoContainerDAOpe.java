package com.siweisoft.service.ui.video.videocontainer;

//by summer on 2017-11-08.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

import java.util.ArrayList;

public class VideoContainerDAOpe extends BaseDAOpe {

    VideoBean videoBean;

    private int type = 0;


    public VideoContainerDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getVideosPager(VideoBean videoBean, int type) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < videoBean.getVideodetail().size(); i++) {
            VideoBean vto = videoBean.getSame();
            VideoPlayFrag playFrag = new VideoPlayFrag();
            playFrag.setArguments(new Bundle());
            playFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, vto);
            playFrag.getArguments().putSerializable(ValueConstant.DATA_DATA2, vto.getVideodetail().get(i));
            playFrag.getArguments().putInt(ValueConstant.DATA_TYPE, type);
            fragments.add(playFrag);
        }
        return fragments;
    }


    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
