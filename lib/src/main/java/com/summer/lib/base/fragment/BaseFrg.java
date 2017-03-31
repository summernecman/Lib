package com.summer.lib.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;

/**
 * Created by ${viwmox} on 2016-11-10.
 */
public class BaseFrg extends Fragment {

    protected FragmentActivity activity;

    protected LayoutInflater layoutInflater;

    protected Handler handler = new Handler();

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
