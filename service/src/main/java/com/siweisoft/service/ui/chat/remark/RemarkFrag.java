package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;
import com.siweisoft.service.ui.main.EMMsgListener;

public class RemarkFrag extends BaseServerFrag<RemarkUIOpe, RemarkDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        setTitleBean(new TitleBean("返回", "评论", "确定"));
        Fragment videofragment = new VideoChatFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ValueConstant.DATA_DATA, getP().getD().getVideoBean());
        bundle.putBoolean(Value.DATA_INTENT, getArguments().getBoolean(Value.DATA_INTENT, false));
        videofragment.setArguments(bundle);
        FragmentUtil2.getInstance().add(fragment.getActivity(), Value.FULLSCREEN, videofragment);
        getP().getU().initRatingBar(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().ratingbar = (float) o;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.ftv_right).setVisibility(View.GONE);
        getActivity().findViewById(R.id.ftv_right).setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
    }

    @Override
    public void doThing() {
        getP().getU().initTips(getP().getD().getData());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right:
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
                commentBean.setFromuser(Value.userBean.getPhone());
                commentBean.setTouser(getP().getD().getVideoBean().getOthername());
                commentBean.setRate(getP().getD().ratingbar);
                commentBean.setRemark(getP().getU().getRemark());
                commentBean.setTips(GsonUtil.getInstance().toJson(getP().getD().getData()));
                commentBean.setVideoname(getP().getD().getVideoBean().getFile());
                commentBean.setFromid(Value.userBean.getId());
                commentBean.setToid(getP().getD().getVideoBean().getOtherUser().getId());
                getP().getD().videoI.commentVideo(commentBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                    }
                });
                break;
        }
    }


    @Override
    public void dealMesage(final MessageEvent event) {
        super.dealMesage(event);
        if (EMMsgListener.class.getName().equals(event.sender)) {
            getActivity().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
            getP().getD().getVideoBean().setFile((String) event.data);
        } else {
            VideoBean videoBean = (VideoBean) event.data;
            if (NullUtil.isStrEmpty(videoBean.getFile())) {
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                return;
            }
            getP().getD().updateVideo(videoBean, new OnFinishListener() {
                @Override
                public void onFinish(Object o) {

                    getActivity().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
                    getP().getD().getVideoBean().setFile(((VideoBean) (event.data)).getFile());
                }
            });
        }

    }
}
