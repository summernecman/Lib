package com.android.lib.util.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.lib.R;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ${viwmox} on 2016-11-10.
 */
public class FragManager {
    private static FragManager instance;

    private int index = 0;


    private HashMap<Integer, ArrayList<Fragment>> fragMaps = new HashMap<>();


    private ArrayList<Integer> containsView = new ArrayList<>();


    public static FragManager getInstance() {
        if (instance == null) {
            instance = new FragManager();

        }
        return instance;
    }

    public void init(ArrayList<Integer> ints) {
        LogUtil.E(containsView.size() + "--" + fragMaps);
        containsView.clear();
        for (int i = 0; i < ints.size(); i++) {
            containsView.add(ints.get(i));
        }

        fragMaps.clear();
        for (int i = 0; i < containsView.size(); i++) {
            fragMaps.put(i, new ArrayList<Fragment>());
        }
    }

    public void clear() {
        containsView.clear();
        fragMaps.clear();
    }


    public void finish(FragmentManager manager, int index) {
        if (fragMaps.get(index) != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            Bundle b = null;
            if (fragMaps.get(index).size() > 0) {
                Fragment fragment = fragMaps.get(index).get(fragMaps.get(index).size() - 1);
                b = fragment.getArguments();
                transaction.remove(fragment);
                fragMaps.get(index).remove(fragMaps.get(index).size() - 1);
                fragment = null;
                if ((fragMaps.get(index).size() - 1) >= 0) {
                    if (fragMaps.get(index).get(fragMaps.get(index).size() - 1) instanceof BaseUIFrag) {
                        BaseUIFrag fragment2 = (BaseUIFrag) fragMaps.get(index).get(fragMaps.get(index).size() - 1);
                        transaction.show(fragment2);
                        if (fragment2.getArguments() != null && fragment2.getArguments().getInt(ValueConstant.FARG_REQ) != 0) {
                            fragment2.onResult(fragment2.getArguments().getInt(ValueConstant.FARG_REQ), b);
                        }
                    }
                }
                transaction.commitAllowingStateLoss();
            }
        }
    }

    public void finish(FragmentManager manager, int index, Bundle bundle) {
        if (fragMaps.get(index) != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            Fragment fragment = fragMaps.get(index).get(fragMaps.get(index).size() - 1);
            if (fragment != null) {
                transaction.remove(fragment);
                fragMaps.get(index).remove(fragMaps.get(index).size() - 1);
                fragment = null;
                if ((fragMaps.get(index).size() - 1) >= 0) {
                    if (fragMaps.get(index).get(fragMaps.get(index).size() - 1) instanceof BaseUIFrag) {
                        BaseUIFrag fragment2 = (BaseUIFrag) fragMaps.get(index).get(fragMaps.get(index).size() - 1);
                        transaction.show(fragment2);
                        if (fragment2.getArguments() != null && fragment2.getArguments().getInt(ValueConstant.FARG_REQ) != 0) {
                            fragment2.onResult(fragment2.getArguments().getInt(ValueConstant.FARG_REQ), bundle);
                        }
                    }

                }
                transaction.commitAllowingStateLoss();
            }
        }
    }

    public void finish(Activity activity) {
        fragMaps.clear();
    }

    public void startFragment(FragmentManager manager, int index, Fragment fragment) {
        this.index = index;
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
            }
            Bundle bundle = fragment.getArguments();
            if (bundle == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);

            transaction.add(containsView.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void startFragment(FragmentManager manager, int index, Fragment fragment, Bundle bundle) {
        this.index = index;
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
            }
            Bundle b = fragment.getArguments();
            if (b == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);
            if (bundle != null) {
                fragment.getArguments().putAll(bundle);
            }
            transaction.add(containsView.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    public void startFragment(FragmentManager manager, int index, Fragment fragment, Bundle bundle, View view, int id) {
        this.index = index;
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
            }
            Bundle b = fragment.getArguments();
            if (b == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);
            if (bundle != null) {
                int[] ints = new int[3];
                ints[3] = id;
                view.getLocationInWindow(ints);
                bundle.putIntArray(ValueConstant.DATA_INTENT3, ints);
                fragment.getArguments().putAll(bundle);
            }
            transaction.add(containsView.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void startFragmentForResult(FragmentManager manager, int index, Fragment fragment, Bundle bundle, int req) {
        this.index = index;
        if (fragMaps.get(index) == null) {
            fragMaps.put(index, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragment != null) {
            if (fragMaps.get(index).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMaps.get(index).get(fragMaps.get(index).size() - 1));
                if (fragMaps.get(index).get(fragMaps.get(index).size() - 1).getArguments() == null) {
                    fragMaps.get(index).get(fragMaps.get(index).size() - 1).setArguments(new Bundle());
                }
                fragMaps.get(index).get(fragMaps.get(index).size() - 1).getArguments().putInt(ValueConstant.FARG_REQ, req);
            }
            Bundle b = fragment.getArguments();
            if (b == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, index);
            if (bundle != null) {
                fragment.getArguments().putAll(bundle);
            }
            transaction.add(containsView.get(index), fragment, fragment.getClass().getSimpleName());
            fragMaps.get(index).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void clearTop(FragmentManager manager, int positon) {
        this.index = positon;
        ArrayList<Fragment> fragments = fragMaps.get(positon);
        if (fragments.size() > 1) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            for (int j = fragments.size() - 1; j > 0; j--) {
                transaction.remove(fragments.get(j));
                fragments.remove(j);
                if (fragments.get(j - 1) != null) {
                    transaction.show(fragments.get(j - 1));
                }
            }
            transaction.commitAllowingStateLoss();
        }
    }


    public void clearTopWith(FragmentManager manager, int positon) {
        this.index = positon;
        ArrayList<Fragment> fragments = fragMaps.get(positon);
        if (fragments.size() > 1) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            for (int j = fragments.size() - 1; j > 0; j--) {
                transaction.remove(fragments.get(j));
                fragments.remove(j);
                if (fragments.get(j - 1) != null) {
                    transaction.show(fragments.get(j - 1));
                }
            }
            transaction.commitAllowingStateLoss();
        }
    }


    public void clear(FragmentManager manager, int positon) {
        this.index = positon;
        ArrayList<Fragment> fragments = fragMaps.get(positon);
        if (fragments.size() > 0) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
            for (int j = fragments.size() - 1; j >= 0; j--) {
                transaction.remove(fragments.get(j));
                fragments.remove(j);
                if (j >= 1 && fragments.get(j - 1) != null) {
                    transaction.show(fragments.get(j - 1));
                }
            }
            transaction.commitAllowingStateLoss();
        }
    }


    public ArrayList<Integer> getContainsView() {
        return containsView;
    }

    public HashMap<Integer, ArrayList<Fragment>> getFragMaps() {
        return fragMaps;
    }

    public Fragment getCurrentClass(int index) {
        return fragMaps.get(index).get(fragMaps.get(index).size() - 1);
    }
}
