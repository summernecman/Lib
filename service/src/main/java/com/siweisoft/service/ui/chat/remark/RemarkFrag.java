package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;

public class RemarkFrag extends BaseServerFrag<RemarkUIOpe, RemarkDAOpe> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getD().setVideoBean((VideoBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        setTitleBean(new TitleBean("返回", "评论", "确定"));
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
                getActivity().findViewById(R.id.ftv_right).setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.ftv_right).setOnClickListener(this);
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
