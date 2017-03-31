package com.siweisoft.app.main;

//by summer on 2017-03-31.

import android.os.Bundle;

import com.siweisoft.app.base.AppFrag;
import com.summer.lib.base.ope.BaseOpes;

public class MainFrag extends AppFrag<HomeUIOpe,HomeDAOpe> {


    @Override
    public BaseOpes<HomeUIOpe, HomeDAOpe> createOpes() {
        return new BaseOpes<>(new HomeUIOpe(activity),new HomeDAOpe());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOpes().getUiOpe().a(getOpes().getDaOpe().ints);
    }




}
