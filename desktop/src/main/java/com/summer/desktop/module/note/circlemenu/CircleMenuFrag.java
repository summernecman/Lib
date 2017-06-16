package com.summer.desktop.module.note.circlemenu;

//by summer on 2017-06-06.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.summer.desktop.R;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;

public class CircleMenuFrag extends BaseUIFrag<CircleMenuUIOpe, CricleMenuDAOpe> {

    OnFinishListener onFinishListener;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getUiOpe().init(fragment, this, onFinishListener);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
        transaction.remove(CircleMenuFrag.this);
        transaction.commit();
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }
}
