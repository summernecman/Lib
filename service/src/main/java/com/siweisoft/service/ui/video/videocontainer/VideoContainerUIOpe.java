package com.siweisoft.service.ui.video.videocontainer;

//by summer on 2017-11-08.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.activity.BaseActivity;
import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragVideocontainerBinding;
import com.siweisoft.service.netdb.video.VideoBean;

import java.util.ArrayList;

public class VideoContainerUIOpe extends BaseUIOpe<FragVideocontainerBinding> {


    public VideoContainerUIOpe(Context context) {
        super(context);
    }

    public void initViewPager(Fragment f, final ArrayList<Fragment> fragments) {
        bind.vpVp.setOffscreenPageLimit(fragments.size());
        BaseActivity activity = (BaseActivity) context;
        bind.vpVp.setAdapter(new AppBasePagerAdapter(f.getChildFragmentManager(), context) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    public void switchFragment() {
        bind.vpVp.setCurrentItem(1 - bind.vpVp.getCurrentItem(), true);
    }

    public void initSwitchIcon(VideoBean videoBean) {
        if (videoBean.getVideodetail().size() > 0) {
            bind.ftvRight2.setVisibility(View.VISIBLE);
        } else {
            bind.ftvRight2.setVisibility(View.GONE);
        }
    }
}
