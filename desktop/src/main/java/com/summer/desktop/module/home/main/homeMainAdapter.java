package com.summer.desktop.module.home.main;

//by summer on 2017-06-07.

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.summer.desktop.module.app.apps.AppsFrag;
import com.summer.desktop.module.day.main.DayFrag;
import com.summer.desktop.module.note.main.NoteMainFrag;

import java.util.ArrayList;

public class homeMainAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();


    FragmentManager fm;


    public homeMainAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        fragments.add(new DayFrag());
        fragments.add(new AppsFrag());
        fragments.add(new NoteMainFrag());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return 3;
    }
}
