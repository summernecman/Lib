package com.summer.lib.util;

import android.app.Activity;

import java.util.Stack;

/**
 * activity 管理
 * Created by ${viwmox} on 2016-05-12.
 */
public class ActivityUtil {

    /**
     * 记录activity的集合
     */
    Stack<Activity> activityStack;

    private static ActivityUtil instance;

    public ActivityUtil getInstance() {
        if (instance == null) {
            instance = new ActivityUtil();
        }
        return instance;
    }


    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }


    /**
     * 获取当前activity
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 退出
     */
    public void exit() {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null) {
                activity.finish();
                activity = null;
            }
        }
        System.gc();
        System.exit(0);
    }
}
