package com.android.lib.base.ope;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;

import com.android.lib.bean.AppViewHolder;
import com.android.lib.databinding.LayoutBaseuiWithouttitleBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * ui处理操作者 处理对象 uibean fragment view
 */
public class BaseUIOpe<A extends ViewDataBinding> {

    public AppViewHolder viewHolder;
    public int[] variableId;
    public A bind;
    protected Context context;


    public BaseUIOpe(Context context) {
        this.context = context;
        bind = initViewDataBinding();
        viewHolder = new AppViewHolder(bind);
        bind.executePendingBindings();
    }


    public A initViewDataBinding() {
        A viewDataBinding = null;
        if (viewDataBinding == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Method method = null;
                try {
                    method = a.getMethod("inflate", LayoutInflater.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    viewDataBinding = (A) method.invoke(null, LayoutInflater.from(context));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                viewDataBinding = (A) LayoutBaseuiWithouttitleBinding.inflate(LayoutInflater.from(context));
            }
        }
        return viewDataBinding;
    }

    public A getBind() {
        return bind;
    }

    public int[] getVariableId() {
        return variableId;
    }

    public void setVariableId(int[] variableId) {
        this.variableId = variableId;
    }
}
