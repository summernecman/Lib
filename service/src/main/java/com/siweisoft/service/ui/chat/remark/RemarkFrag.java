package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
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
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

public class RemarkFrag extends BaseUIFrag<RemarkUIOpe, RemarkDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initTitle();
        getView().findViewById(R.id.ftv_right).setVisibility(View.GONE);
        Fragment videofragment = new VideoChatFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ValueConstant.DATA_DATA, getP().getD().getVideoBean());
        videofragment.setArguments(bundle);
        FragmentUtil2.getInstance().add(fragment.getActivity(), Value.ROOTID_TWO, videofragment);
        getP().getU().initRatingBar(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().ratingbar = (float) o;
            }
        });
        AnyChatCoreSDK.getInstance(activity).SetTextMessageEvent(new AnyChatTextMsgEvent() {
            @Override
            public void OnAnyChatTextMessage(int i, int i1, boolean b, String s) {
                getView().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
                LogUtil.E(i + "" + i1 + "" + b + "" + s);
                ToastUtil.getInstance().showLong(activity, i + "@" + i1 + "@" + s);
                getP().getD().getVideoBean().setFile(s);
            }
        });
    }

    @Override
    public void doThing() {
        getP().getU().initTips(getP().getD().getData());
    }

    @OnClick({R.id.ftv_back, R.id.ftv_title, R.id.ftv_right})
    public void onClickEvent(View v) {
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
            case R.id.ftv_title:

                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CommentBean bean) {
        getView().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
        getP().getD().getVideoBean().setFile(bean.getVideoname());
    }

    ;
}
