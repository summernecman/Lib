package com.android.lib.util;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.android.lib.R;
import com.android.lib.base.interf.OnFinishListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * fragment操作的工具类
 * Created by summer on 2016/4/16 0016 16:25.
 */
public class FragmentUtil2 {

    public static FragmentUtil2 instance;

    //public static ArrayList<Fragment> fragments = new ArrayList<>();

    public static HashMap<Integer, ArrayList<Fragment>> fragMap = new HashMap<>();

    Handler handler = new Handler();

    public static FragmentUtil2 getInstance() {
        if (instance == null) {
            instance = new FragmentUtil2();
        }
        return instance;
    }


    public void add(final FragmentActivity fragmentActivity, int id, Fragment now) {
        if (fragMap.get(id) == null) {
            fragMap.put(id, new ArrayList<Fragment>());
        }
        if (fragMap.get(id) != null && fragMap.get(id).size() > 0) {
            Fragment fragment = fragMap.get(id).get(fragMap.get(id).size() - 1);
            fragMap.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.hide(fragment);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        } else {
            fragMap.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        }
    }

    public void addNoAnim(final FragmentActivity fragmentActivity, int id, Fragment now) {
        if (fragMap.get(id) == null) {
            fragMap.put(id, new ArrayList<Fragment>());
        }
        if (fragMap.get(id) != null && fragMap.get(id).size() > 0) {
            Fragment fragment = fragMap.get(id).get(fragMap.get(id).size() - 1);
            fragMap.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.hide(fragment);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        } else {
            fragMap.get(id).add(now);
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        }
    }



    public void add(final Fragment f, int id, Fragment now) {
        if (fragMap.get(id) == null) {
            fragMap.put(id, new ArrayList<Fragment>());
        }
        if (fragMap.get(id) != null && fragMap.get(id).size() > 0) {
            Fragment fragment = fragMap.get(id).get(fragMap.get(id).size() - 1);
            fragMap.get(id).add(now);
            FragmentTransaction transaction = f.getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.hide(fragment);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        } else {
            fragMap.get(id).add(now);
            FragmentTransaction transaction = f.getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out);
            transaction.add(id, now);
            transaction.commitAllowingStateLoss();
        }
    }


    public void removeTop(final FragmentActivity fragmentActivity, int id) {
        if (fragMap.get(id) != null && fragMap.get(id).size() > 0) {
            LogUtil.E("befroe");
            final Fragment now = fragMap.get(id).get(fragMap.get(id).size() - 1);
            final Fragment old;
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            transaction.hide(now);
            if (fragMap.get(id).size() > 1) {
                LogUtil.E("after");
                old = fragMap.get(id).get(fragMap.get(id).size() - 2);
                transaction.show(old);
            }
            transaction.commitAllowingStateLoss();
            fragMap.get(id).remove(now);
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

    public void removeTopRightNow(final FragmentActivity fragmentActivity, int id) {
        if (fragMap.get(id) != null && fragMap.get(id).size() > 0) {
            LogUtil.E("befroe");
            final Fragment now = fragMap.get(id).get(fragMap.get(id).size() - 1);
            final Fragment old;
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            transaction.hide(now);
            if (fragMap.get(id).size() > 1) {
                LogUtil.E("after");
                old = fragMap.get(id).get(fragMap.get(id).size() - 2);
                transaction.show(old);
            }
            transaction.commitAllowingStateLoss();
            fragMap.get(id).remove(now);
            FragmentTransaction transaction1 = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction1.remove(now);
            transaction1.commitAllowingStateLoss();
        }
    }

    public void removeTop(final FragmentActivity fragmentActivity, int id, OnFinishListener onFinishListener) {
        if (fragMap.get(id) != null && fragMap.get(id).size() > 0) {
            LogUtil.E("befroe");
            final Fragment now = fragMap.get(id).get(fragMap.get(id).size() - 1);
            final Fragment old;
            FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            transaction.hide(now);
            if (fragMap.get(id).size() > 1) {
                LogUtil.E("after");
                old = fragMap.get(id).get(fragMap.get(id).size() - 2);
                transaction.show(old);
            }
            transaction.commitAllowingStateLoss();
            fragMap.get(id).remove(now);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                    transaction.remove(now);
                    transaction.commitAllowingStateLoss();
                }
            }, 500);
        } else {
            onFinishListener.onFinish(fragMap.get(id));
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

    public void clear(int id) {
        if (fragMap.get(id) != null) {
            fragMap.get(id).clear();
        }
    }

    public void clear() {
        Iterator i = fragMap.keySet().iterator();
        while (i.hasNext()) {
            Integer in = (Integer) i.next();
            clear(in);

        }
    }

    public void initClear(FragmentActivity fragmentActivity, int id) {
        clear(id);
        ArrayList<Fragment> fragments = (ArrayList<Fragment>) fragmentActivity.getSupportFragmentManager().getFragments();
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        for (int i = 0; fragments != null && i < fragments.size(); i++) {
            transaction.remove(fragments.get(i));
        }
        transaction.commitAllowingStateLoss();
    }

    public void initClear(FragmentActivity fragmentActivity) {
        ArrayList<Fragment> fragments = (ArrayList<Fragment>) fragmentActivity.getSupportFragmentManager().getFragments();
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        for (int i = 0; fragments != null && i < fragments.size(); i++) {
            try {
                transaction.remove(fragments.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        Iterator i = fragMap.keySet().iterator();
        while (i.hasNext()) {
            Integer in = (Integer) i.next();
            ArrayList<Fragment> fragments = fragMap.get(in);
            for (int j = 0; j < fragments.size(); j++) {
                LogUtil.E(fragments.get(j).getClass().getName());
            }

        }
    }
}
