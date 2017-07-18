package com.summer.factory.ui.base;

//by summer on 2017-07-17.

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseOpes;
import com.android.lib.util.StatusBarUtil;
import com.summer.factory.R;
import com.summer.factory.value.FactoryValue;

import java.util.ArrayList;

import butterknife.OnClick;

public abstract class BaseFactoryAct extends BaseUIActivity<BaseFactoryUIOpe, BaseFactoryDAOpe> implements OnFinishListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.getInstance().setStatusBarColor(activity, getResources().getColor(com.android.lib.R.color.color_blue_400));
        getOpes().getDa().setFragments(initFrag());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ArrayList<TabView.Txt> txts = new ArrayList<>();
        for (int i = 0; i < getOpes().getDa().getFragments().size(); i++) {
            fragmentTransaction.add(FactoryValue.BASE_ID, getOpes().getDa().getFragments().get(i));
            if (i > 0) {
                fragmentTransaction.hide(getOpes().getDa().getFragments().get(i));
            }
            txts.add(new TabView.Txt(getOpes().getDa().getFragments().get(i).getArguments().getString(FactoryValue.FRAG_TITLE)));
        }
        getOpes().getUi().viewDataBinding.tabview.setTxt(txts);
        fragmentTransaction.commitAllowingStateLoss();
        getOpes().getUi().initRadioButton(this);
    }

    /**
     * 获取操作类
     */
    public BaseOpes<BaseFactoryUIOpe, BaseFactoryDAOpe> getOpes() {
        if (opes == null) {
            opes = new BaseOpes<>(new BaseFactoryUIOpe(activity), new BaseFactoryDAOpe(activity));
        }
        return opes;
    }

    @OnClick({R.id.ftv_back, R.id.ftv_title, R.id.ftv_right})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.ftv_back:
                finish();
                break;
            case R.id.ftv_title:
                break;
            case R.id.ftv_right:
                finish();
                break;
        }
    }

    public abstract ArrayList<Fragment> initFrag();


    @Override
    public void onFinish(Object o) {
        int position = (int) o;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < getOpes().getDa().getFragments().size(); i++) {
            if (i == position) {
                fragmentTransaction.show(getOpes().getDa().getFragments().get(i));
            } else {
                fragmentTransaction.hide(getOpes().getDa().getFragments().get(i));
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}
