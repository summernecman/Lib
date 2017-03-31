package com.summer.lib.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summer.lib.R;
import com.summer.lib.base.ope.BaseDAOpe;
import com.summer.lib.base.ope.BaseOpes;
import com.summer.lib.base.ope.BaseUIOpe;
import com.summer.lib.constant.ValueConstant;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by summer on 2016/4/16 0016 16:03.
 */
public abstract class BaseUIFrag<A extends BaseUIOpe,B extends BaseDAOpe> extends BaseFrg implements View.OnClickListener{


    Unbinder unbinder;

    BaseOpes<A,B> opes;

    protected int index;

    public BaseUIFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            index = getArguments().getInt(ValueConstant.FRAG_POSITION);
        }
        View group = inflater.inflate(getLayoutID(), null);
        ViewGroup parent = (ViewGroup) group.findViewById(R.id.container);
        if(getOpes().getUiOpe()!=null && getOpes().getUiOpe().getUiBean().itemView!=null){
            parent.addView(getOpes().getUiOpe().getUiBean().itemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        unbinder = ButterKnife.bind(this, group);
        return group;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public int getLayoutID() {
        return R.layout.layout_baseui;
    }

    public BaseOpes<A, B> getOpes() {
        if(opes==null){
            opes = createOpes();
        }
        return opes;
    }

    public abstract BaseOpes<A, B>  createOpes();

    @Override
    public void onClick(View v) {

    }

    public void onResult(int req, Bundle bundle) {

    }
}
