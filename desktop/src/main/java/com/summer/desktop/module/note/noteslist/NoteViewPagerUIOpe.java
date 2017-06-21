package com.summer.desktop.module.note.noteslist;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.summer.desktop.bean.dabean.Note;
import com.summer.desktop.databinding.FragNoteNotesBinding;
import com.summer.desktop.module.note.main.NoteMainFrag;
import com.summer.desktop.util.TitleUtil;
import com.summer.lib.base.ope.BaseUIBean;
import com.summer.lib.view.bottommenu.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class NoteViewPagerUIOpe extends BaseUIBean<FragNoteNotesBinding> {

    ArrayList<Note> notes;

    int position = 0;

    public NoteViewPagerUIOpe(Context context) {
        super(context);
    }


    public void initpager(final Fragment fragment, final ArrayList<Note> notes, int position) {
        viewDataBinding.vpNotes.setAdapter(new NoteViewPagerAdapter(fragment.getChildFragmentManager(), notes));
        viewDataBinding.vpNotes.setCurrentItem(position);
        viewDataBinding.vpNotes.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TitleUtil.getInstance().getName().remove(TitleUtil.getInstance().getName().size() - 1);
                TitleUtil.getInstance().getName().add(notes.get(position).getName());
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.dealer = NoteMainFrag.class.getName();
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
