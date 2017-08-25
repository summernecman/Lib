package com.android.lib.util;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.android.lib.R;
import com.android.lib.base.interf.OnFinishListener;

import java.util.ArrayList;

/**
 * fragment操作的工具类
 * Created by summer on 2016/4/16 0016 16:25.
 */
public class FragmentUtil {

    public static FragmentUtil instance;

    public static ArrayList<Fragment> fragments = new ArrayList<>();

    Handler handler = new Handler();

    public static FragmentUtil getInstance() {
        if (instance == null) {
            instance = new FragmentUtil();
        }
        return instance;
    }

//    public void add(final FragmentActivity fragmentActivity, Fragment now) {
//        if (fragments != null && fragments.size() > 0) {
//            Fragment fragment = fragments.get(fragments.size() - 1);
//            fragments.add(now);
//            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
//            transaction.hide(fragment);
//            transaction.add(R.id.root, now);
//            transaction.commit();
//        } else {
//            fragments.add(now);
//            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
//            transaction.add(R.id.root, now);
//            transaction.commit();
//        }
//    }

    public void add(final FragmentActivity fragmentActivity, int id, Fragment now) {
        if (fragments != null && fragments.size() > 0) {
            Fragment fragment = fragments.get(fragments.size() - 1);
            fragments.add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.hide(fragment);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        } else {
            fragments.add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        }
    }

    public void add(final Fragment f, int id, Fragment now) {
        if (fragments != null && fragments.size() > 0) {
            Fragment fragment = fragments.get(fragments.size() - 1);
            fragments.add(now);
            FragmentTransaction transaction = f.getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.hide(fragment);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        } else {
            fragments.add(now);
            FragmentTransaction transaction = f.getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        }
    }


    public void removeTop(final FragmentActivity fragmentActivity) {
        if (fragments != null && fragments.size() > 0) {
            LogUtil.E("befroe");
            final Fragment now = fragments.get(fragments.size() - 1);
            final Fragment old;
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            transaction.hide(now);
            if (fragments.size() > 1) {
                LogUtil.E("after");
                old = fragments.get(fragments.size() - 2);
                transaction.show(old);
            }
            transaction.commitAllowingStateLoss();
            fragments.remove(now);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                    transaction.remove(now);
                    transaction.commitAllowingStateLoss();
                }
            }, 500);
        }
    }

    public void removeTop(final FragmentActivity fragmentActivity, OnFinishListener onFinishListener) {
        if (fragments != null && fragments.size() > 0) {
            LogUtil.E("befroe");
            final Fragment now = fragments.get(fragments.size() - 1);
            final Fragment old;
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            transaction.hide(now);
            if (fragments.size() > 1) {
                LogUtil.E("after");
                old = fragments.get(fragments.size() - 2);
                transaction.show(old);
            }
            transaction.commitAllowingStateLoss();
            fragments.remove(now);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                    transaction.remove(now);
                    transaction.commitAllowingStateLoss();
                }
            }, 500);
        } else {
            onFinishListener.onFinish(fragments);
        }
    }

    public void remove(final FragmentActivity fragmentActivity, Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }

    public void clear() {
        fragments.clear();
    }

    public void initClear(FragmentActivity fragmentActivity) {
        clear();
        ArrayList<Fragment> fragments = (ArrayList<Fragment>) fragmentActivity.getSupportFragmentManager().getFragments();
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        for (int i = 0; fragments != null && i < fragments.size(); i++) {
            transaction.remove(fragments.get(i));
        }
        transaction.commitAllowingStateLoss();
    }


}
