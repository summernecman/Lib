package com.android.lib.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;

/**
 * fragment布局基类
 */
public class BaseFrg extends Fragment {

    /**
     * fragment对应的activity的引用
     */
    protected FragmentActivity activity;
    /**
     * 布局加载器
     */
    protected LayoutInflater layoutInflater;
    /**
     * fragment自身的引用
     */
    protected Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = this;
        layoutInflater = LayoutInflater.from(getActivity());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            activity = (FragmentActivity) context;
        }
    }
}
