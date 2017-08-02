package com.summer.desktop.module.note.notemain;

//by summer on 2017-07-28.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.desktop.databinding.ItemViewpagerBinding;

import java.util.ArrayList;

public class NoteMainUIOpe extends BaseUIOpe<ItemViewpagerBinding> {


    public NoteMainUIOpe(Context context) {
        super(context);
    }

    public void initViewPager(FragmentManager fragmentManager, Context context, final ArrayList<Fragment> fragments) {
        bind.viewpager.setOffscreenPageLimit(fragments.size());
        bind.viewpager.setAdapter(new AppBasePagerAdapter(fragmentManager, context) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }
}
