package com.summer.desktop.module.circlemenu;

//by summer on 2017-06-06.

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.summer.desktop.R;
import com.summer.desktop.databinding.ItemBoomBinding;

import java.io.Serializable;

public class CircleMenuUIOpe extends BaseUIOpe<ItemBoomBinding> {


    public CircleMenuUIOpe(Context context) {
        super(context);
    }

    public void init(final Fragment fragment, View.OnClickListener onClickListener, final OnFinishListener onFinishListener) {
        bind.getRoot().setOnClickListener(onClickListener);
        bind.circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.ic_launcher_round, R.drawable.app)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.note)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.notebook)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.delete)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.rename)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.insert_image)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.insert_link)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        if (onFinishListener != null) {
                            onFinishListener.onFinish(index);
                        }
                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {
                onFinishListener.onFinish(-1);
            }

            @Override
            public void onMenuClosed() {
                onFinishListener.onFinish(-2);
                if (fragment == null) {
                    return;
                }
                FragmentTransaction transaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
                transaction.remove(fragment);
                transaction.commitAllowingStateLoss();
            }

        });
        bind.circleMenu.openMenu();
    }

    public static class Boom {

        public int position;

        public Serializable serializable;

        public String location;

        public Boom(int position) {
            this.position = position;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
