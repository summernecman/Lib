package com.siweisoft.service.ui.video.videocontainer;

//by summer on 2017-11-08.

import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.video.VideoBean;

public class VideoContainerFrag extends BaseServerFrag<VideoContainerUIOpe, VideoContainerDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "视频播放", ""));
        getP().getD().setType(getArguments().getInt(ValueConstant.DATA_TYPE, 0));
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initViewPager(VideoContainerFrag.this, getP().getD().getVideosPager(getP().getD().getVideoBean(), getP().getD().getType()));
        getP().getU().bind.ftvRight2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right2:
                getP().getU().switchFragment();
                break;
        }
    }
}
