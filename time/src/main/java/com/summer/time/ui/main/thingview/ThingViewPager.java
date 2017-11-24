package com.summer.time.ui.main.thingview;

//by summer on 2017-11-24.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;

import com.android.lib.base.activity.BaseActivity;
import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.view.other.AppViewPager;

import java.util.ArrayList;

public class ThingViewPager extends AppViewPager {

    BaseActivity baseActivity;

    public ThingViewPager(Context context) {
        super(context);
    }

    public ThingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void init(final ArrayList<BaseUIFrag> fragments) {
        baseActivity = (BaseActivity) getContext();
        setAdapter(new AppBasePagerAdapter(baseActivity.getSupportFragmentManager(), getContext()) {
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
