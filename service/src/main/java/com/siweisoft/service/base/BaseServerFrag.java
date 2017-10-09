package com.siweisoft.service.base;

//by summer on 17-09-04.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.system.HandleUtil;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.MainAct;

public class BaseServerFrag<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseUIFrag<A, B> {

    TitleBean titleBean;


    private MainAct.OnTitleClick onTitleClick;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HandleUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                doThing();
                initData();
            }
        }, 500);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public TitleBean getTitleBean() {
        return titleBean;
    }

    public void setTitleBean(TitleBean titleBean) {
        this.titleBean = titleBean;
        TextView textView1 = (TextView) getView().findViewById(R.id.ftv_back);
        textView1.setText(titleBean.getLeftTxt());
        textView1.setOnClickListener(this);
        TextView textView2 = (TextView) getView().findViewById(R.id.ftv_title);
        textView2.setText(titleBean.getMidTxt());
        textView2.setOnClickListener(this);
        TextView textView3 = (TextView) getView().findViewById(R.id.ftv_right);
        textView3.setText(titleBean.getRightTxt());
        textView3.setOnClickListener(this);
        TextView textView4 = (TextView) getView().findViewById(R.id.ftv_right2);
        textView4.setText(titleBean.getRightTxt2());
        textView4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right:
                break;
            case R.id.ftv_title:
                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                break;
            case R.id.ftv_right2:
                break;
        }
    }

    public void initOnTitleClick(MainAct.OnTitleClick onTitleClick) {

    }
}
