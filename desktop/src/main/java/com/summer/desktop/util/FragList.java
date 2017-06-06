package com.summer.desktop.util;

//by summer on 2017-05-23.

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.summer.desktop.R;

import java.util.ArrayList;

public class FragList {

    public static FragList instance;

    public static ArrayList<Fragment> fragments = new ArrayList<>();

    Handler handler = new Handler();

    public static FragList getInstance() {
        if (instance == null) {
            instance = new FragList();
        }
        return instance;
    }

    public void add(final FragmentActivity fragmentActivity, Fragment now) {
        if (fragments != null && fragments.size() > 0) {
            Fragment fragment = fragments.get(fragments.size() - 1);
            fragments.add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.hide(fragment);
            transaction.add(R.id.root, now);
            transaction.commit();
        } else {
            fragments.add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.add(R.id.root, now);
            transaction.commit();
        }
    }


    public void removeTop(final FragmentActivity fragmentActivity) {
        if (fragments != null && fragments.size() > 1) {
            final Fragment now = fragments.get(fragments.size() - 1);
            final Fragment old = fragments.get(fragments.size() - 2);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            transaction.hide(now);
            transaction.show(old);
            transaction.commit();
            fragments.remove(now);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                    transaction.remove(now);
                    transaction.commit();
                }
            }, 500);
        }


    }

    public void clear() {
        fragments.clear();
    }
}
