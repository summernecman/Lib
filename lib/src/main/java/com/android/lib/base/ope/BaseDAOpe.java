package com.android.lib.base.ope;

import android.content.Context;

import com.android.lib.bean.LayoutDABean;

import java.util.ArrayList;

/**
 * 数据处理的操作列
 */
public class BaseDAOpe implements BaseOpe {

    /**
     * 上下文
     */
    protected Context context;

    private BaseDAOpe() {

    }

    public BaseDAOpe(Context context) {
        this.context = context;
    }

    public LayoutDABean getData(Object[] objects) {
        LayoutDABean bean = new LayoutDABean();
        for (int i = 0; i < objects.length; i++) {
            bean.data[i].set(objects[i]);
        }
        return bean;
    }

    public ArrayList<LayoutDABean> getData(Object[][] objects) {
        ArrayList<LayoutDABean> data = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            data.add(getData(objects[i]));
        }
        return data;
    }
}