package com.summer.lib.base.ope;

import android.content.Context;

/**
 * 数据处理的操作列
 */
public class BaseDAOpe implements BaseOpe {

    /**上下文*/
    protected Context context;

    private  BaseDAOpe(){

    }

    public BaseDAOpe(Context context){
        this.context = context;
    }
}