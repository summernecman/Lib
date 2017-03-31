package com.summer.lib.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.summer.lib.R;
import com.summer.lib.base.fragment.BaseUIFrag;

import java.util.ArrayList;

/**
 * fragment操作的工具类
 * Created by summer on 2016/4/16 0016 16:25.
 */
public class FragmentUtil {

    private static FragmentUtil instance;

    public static FragmentUtil getInstance() {
        if (instance == null) {
            instance = new FragmentUtil();
        }
        return instance;
    }

    public void addToContaier(FragmentActivity activity, ArrayList<Fragment> fragments, int resid) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            transaction.add(resid, fragments.get(i));
        }
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addToContaier(FragmentActivity activity, Fragment fragment, int resid) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
        transaction.add(resid, fragment, fragment.getClass().getSimpleName());
        transaction.commit();
    }


    public void addToContaierWithOutAnim(FragmentActivity activity, Fragment fragment, int resid) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(resid, fragment, fragment.getClass().getSimpleName());
        try {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToContaier(FragmentActivity activity, Fragment thisf, Fragment nextf, int resid) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
        transaction.hide(thisf);
        transaction.add(resid, nextf, nextf.getClass().getSimpleName());
        transaction.commit();
    }

    public void addToContaier(FragmentActivity activity, Fragment fragment, int resid, String tag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
        transaction.add(resid, fragment, tag);
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void removeFrag(FragmentActivity activity, Fragment thisf, String tag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
        transaction.remove(thisf);
        if (activity.getSupportFragmentManager().findFragmentByTag(tag) != null) {
            transaction.show(activity.getSupportFragmentManager().findFragmentByTag(tag));
        }

        transaction.commit();
    }


    public void removeFrag(FragmentActivity activity, Fragment thisf, String tag, Bundle bundle) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
        transaction.remove(thisf);
        if (activity.getSupportFragmentManager().findFragmentByTag(tag) != null) {
            if (activity.getSupportFragmentManager().findFragmentByTag(tag) instanceof BaseUIFrag) {
                BaseUIFrag drawerLayoutFrag = (BaseUIFrag) activity.getSupportFragmentManager().findFragmentByTag(tag);
                drawerLayoutFrag.onResult(-1, bundle);
            }
            transaction.show(activity.getSupportFragmentManager().findFragmentByTag(tag));
        }

        transaction.commit();
    }


}
