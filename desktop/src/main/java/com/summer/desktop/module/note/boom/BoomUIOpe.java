package com.summer.desktop.module.note.boom;

//by summer on 2017-06-06.

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.summer.desktop.R;
import com.summer.desktop.bean.uibean.BoomFragUIBean;
import com.summer.lib.base.ope.BaseUIOpe;

import org.greenrobot.eventbus.EventBus;

public class BoomUIOpe extends BaseUIOpe<BoomFragUIBean> {


    public BoomUIOpe(Context context) {
        super(context, new BoomFragUIBean(context, null));
    }


    public void init(final Fragment fragment, View.OnClickListener onClickListener) {
        getUiBean().itemView.setOnClickListener(onClickListener);
        getUiBean().getCircleMenu().setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.ic_launcher_round, R.drawable.app)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.note)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.notebook)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.delete)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.rename)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        EventBus.getDefault().post(new Boom(index));
                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {
                FragmentTransaction transaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
                transaction.remove(fragment);
                transaction.commit();
            }

        });
        getUiBean().getCircleMenu().openMenu();
    }

    public static class Boom {

        public int position;

        public Boom(int position) {
            this.position = position;
        }
    }
}
