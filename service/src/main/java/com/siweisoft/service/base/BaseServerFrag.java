package com.siweisoft.service.base;

//by summer on 17-09-04.

import android.view.View;
import android.widget.TextView;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.MainAct;

public class BaseServerFrag<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseUIFrag<A, B> {

    TitleBean titleBean;


    private MainAct.OnTitleClick onTitleClick;


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
        if (getView() == null) {
            return;
        }
        View v1 = getView().findViewById(R.id.ftv_back);
        if (v1 instanceof TextView) {
            TextView textView1 = (TextView) v1;
            textView1.setText(titleBean.getLeftTxt());
            textView1.setOnClickListener(this);
        }

        View v2 = getView().findViewById(R.id.ftv_title);
        if (v2 instanceof TextView) {
            TextView textView2 = (TextView) v2;
            textView2.setText(titleBean.getMidTxt());
            textView2.setOnClickListener(this);
        }

        View v3 = getView().findViewById(R.id.ftv_right);
        if (v3 instanceof TextView) {
            TextView textView3 = (TextView) v3;
            textView3.setText(titleBean.getRightTxt());
            textView3.setOnClickListener(this);
        }


        View v4 = getView().findViewById(R.id.ftv_right2);
        if (v4 instanceof TextView) {
            TextView textView4 = (TextView) v4;
            textView4.setText(titleBean.getRightTxt2());
            textView4.setOnClickListener(this);
        }
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
                if (FragmentUtil2.getInstance().getFragMap() != null && FragmentUtil2.getInstance().getFragMap().get(Value.getNowRoot()) != null) {
                    if (FragmentUtil2.getInstance().getFragMap().get(Value.getNowRoot()).size() <= 1) {
                        return;
                    }
                }
                FragmentUtil2.getInstance().removeTopRightNow(activity, Value.getNowRoot());
                break;
            case R.id.ftv_right2:
                break;
        }
    }

    public void initOnTitleClick(MainAct.OnTitleClick onTitleClick) {

    }
}
