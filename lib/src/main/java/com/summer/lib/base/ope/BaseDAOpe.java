package com.summer.lib.base.ope;

import android.content.Context;


public class BaseDAOpe implements BaseOpe {


    protected Context context;
    private  BaseDAOpe(){

    }

    public BaseDAOpe(Context context){
        this.context = context;
    }
}