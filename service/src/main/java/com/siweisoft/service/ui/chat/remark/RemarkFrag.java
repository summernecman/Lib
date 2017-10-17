package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.system.SystemUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.dialog.DialogFrag;

public class RemarkFrag extends BaseServerFrag<RemarkUIOpe, RemarkDAOpe> {


    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.ftv_right).setOnClickListener(this);
    }


    @Override
    public void doThing() {
        getP().getU().setFront(activity);
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        setTitleBean(new TitleBean("返回", "评论", "", "确定"));
        getP().getU().initRatingBar(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().ratingbar = (float) o;
            }
        });

        getP().getU().initTop(getP().getD().getOtherUser(getP().getD().getVideoBean()));
        getP().getU().bind.tvName.setText(getP().getD().getOtherUser(getP().getD().getVideoBean()).getName());

        //收到文件内容为空 == 录像不是我录的
        if (NullUtil.isStrEmpty(getP().getD().getVideoBean().getFile())) {
            //FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
            return;
        }
        getP().getD().updateVideo(getP().getD().getVideoBean(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                final VideoBean videoBean = (VideoBean) o;
                getActivity().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
                if (SystemUtil.isWiFi(activity)) {
                    getP().getD().uploadVideo(videoBean);
                } else {
                    DialogFrag dialogFrag = new DialogFrag();
                    FragmentUtil2.getInstance().add(activity, Value.FULLSCREEN, dialogFrag);
                    dialogFrag.setOnFinishListener(new OnFinishListener() {
                        @Override
                        public void onFinish(Object o) {
                            switch (((View) o).getId()) {
                                case R.id.tv_receipt:
                                    getP().getD().uploadVideo(videoBean);
                                    break;
                                case R.id.tv_refuse:

                                    break;
                            }
                            FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        getP().getD().getTips(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().setTipsBean((TipsBean) o);
                getP().getU().initTips(getP().getD().getTipsBean());
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right2:
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
                commentBean.setFromuser(Value.getUserInfo().getPhone());
                commentBean.setTouser(getP().getD().getVideoBean().getOthername());
                commentBean.setRate(getP().getD().ratingbar);
                commentBean.setRemark(getP().getU().getRemark());
                commentBean.setTips(GsonUtil.getInstance().toJson(getP().getD().getTipsBean()));
                commentBean.setVideoname(getP().getD().getVideoBean().getFile());
                commentBean.setFromid(Value.getUserInfo().getId());
                commentBean.setToid(getP().getD().getVideoBean().getOtherUser().getId());
                commentBean.setVideoid(getP().getD().getVideoBean().getId());
                getP().getD().videoI.commentVideo(commentBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                    }
                });
                break;
        }
    }
}
