package com.summer.lib.base.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.summer.lib.aplication.LibAplication;
import com.summer.lib.exception.exception.CrashHander;


/**
 * 所有activity的基类
 * 封装一些常用的activity方法
 * Created by summer on 2016/4/15 0015 16:26.
 */
public class BaseActivity extends FragmentActivity {

    //方便引用的自身对象
    protected FragmentActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        LibAplication application = (LibAplication) getApplication();
        application.getActivityHashMap().put(getClass().getSimpleName(), activity);
        application.getActivities().add(activity);
        CrashHander.getInstance().init(this);
    }
}
