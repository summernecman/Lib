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
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.bairuitech.anychat.AnyChatCoreSDK;
import com.bairuitech.anychat.AnyChatTextMsgEvent;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RemarkFrag extends BaseServerFrag<RemarkUIOpe, RemarkDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        setTitleBean(new TitleBean("返回", "评论", "确定"));
        Fragment videofragment = new VideoChatFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ValueConstant.DATA_DATA, getP().getD().getVideoBean());
        videofragment.setArguments(bundle);
        FragmentUtil2.getInstance().add(fragment.getActivity(), Value.FULLSCREEN, videofragment);
        getP().getU().initRatingBar(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().ratingbar = (float) o;
            }
        });
        AnyChatCoreSDK.getInstance(activity).SetTextMessageEvent(new AnyChatTextMsgEvent() {
            @Override
            public void OnAnyChatTextMessage(int i, int i1, boolean b, String s) {
                getActivity().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
                LogUtil.E(i + "" + i1 + "" + b + "" + s);
                ToastUtil.getInstance().showLong(activity, i + "@" + i1 + "@" + s);
                getP().getD().getVideoBean().setFile(s);
            }
        });
        final UserBean userBean = new UserBean();
        userBean.setPhone(getP().getD().getVideoBean().getTophone());
        getP().getD().getChatUserInfo(userBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                UserBean userBean1 = (UserBean) o;
                getP().getU().initTop(userBean1);
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
                getP().getD().videoI.commentVideo(commentBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                    }
                });
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CommentBean bean) {
        getActivity().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
        getP().getD().getVideoBean().setFile(bean.getVideoname());
    }
}
