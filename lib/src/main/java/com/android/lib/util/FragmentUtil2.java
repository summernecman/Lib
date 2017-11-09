package com.android.lib.util;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
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

    private HashMap<Integer, ArrayList<Fragment>> fragMap = new HashMap<>();

    Handler handler = new Handler();

    public static FragmentUtil2 getInstance() {
        if (instance == null) {
            instance = new FragmentUtil2();
        }
        return instance;
    }

    public Fragment getFragment(Class c) {
        Iterator<Integer> keys = fragMap.keySet().iterator();
        while (keys.hasNext()) {
            int k = keys.next();
            for (int i = 0; fragMap.get(k) != null && i < fragMap.get(k).size(); i++) {
                if (fragMap.get(k).get(i).getClass().getName().equals(c.getName())) {
                    return fragMap.get(k).get(i);
                }
            }
        }
        return null;
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void initClear(FragmentActivity fragmentActivity) {
        LogUtil.E(1);
        if (fragmentActivity == null) {
            return;
        }
        ArrayList<Fragment> fragments = (ArrayList<Fragment>) fragmentActivity.getSupportFragmentManager().getFragments();
        LogUtil.E(2);
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        LogUtil.E(3);
        for (int i = 0; fragments != null && i < fragments.size(); i++) {
            LogUtil.E(4);
            transaction.remove(fragments.get(i));
            LogUtil.E(5);
        }
        LogUtil.E(6);
        if (fragmentActivity == null || fragmentActivity.isDestroyed()) {
            return;
        }
        transaction.commitAllowingStateLoss();
        LogUtil.E(7);
        LogUtil.E(8);
    }

    public HashMap<Integer, ArrayList<Fragment>> getFragMap() {
        return fragMap;
    }

    public String print() {
        String s = "print";
        Iterator i = fragMap.keySet().iterator();
        while (i.hasNext()) {
            Integer in = (Integer) i.next();
            ArrayList<Fragment> fragments = fragMap.get(in);
            for (int j = 0; j < fragments.size(); j++) {
                s += fragments.get(j).getClass().getName();
                LogUtil.E(fragments.get(j).getClass().getName());
            }

        }
        return s;
    }
}
