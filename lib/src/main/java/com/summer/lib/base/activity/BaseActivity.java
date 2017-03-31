package com.summer.lib.base.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.summer.lib.aplication.LibAplication;


/**
 * 所有activity的基类
 * 封装一些常用的activity方法
 * Created by summer on 2016/4/15 0015 16:26.
 */
public class BaseActivity extends AppCompatActivity {

    protected Handler handler = new Handler();

    protected FragmentActivity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        LibAplication application = (LibAplication) getApplication();
        application.getActivityHashMap().put(getClass().getSimpleName(), activity);
        application.getActivities().add(activity);
        //  CrashHander.getInstance().init(this);
    }
}
