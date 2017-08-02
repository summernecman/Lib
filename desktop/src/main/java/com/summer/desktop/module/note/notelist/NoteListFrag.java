package com.summer.desktop.module.note.notelist;

//by summer on 2017-07-28.

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.netadapter.OnNetProcessAdapter;
import com.android.lib.util.FragmentUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.summer.desktop.R;
import com.summer.desktop.module.circlemenu.CircleMenuFrag;
import com.summer.desktop.module.note.bean.NoteListResBean;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.note.NoteFrag;
import com.summer.desktop.module.note.notemain.NoteMainFrag;

public class NoteListFrag extends BaseUIFrag<NoteListUIOpe, NoteListDAOpe> {

    @Override
    public void doThing() {

        P().D().setNoteOrBookBean((NoteOrBookBean) getArguments().getSerializable(ValueConstant.DATA_DATA));

        P().U().initRefresh(new OnFinishListener<MaterialRefreshLayout>() {
            @Override
            public void onFinish(final MaterialRefreshLayout o) {
                P().D().getNoteDataOpe().getNoteList(P().D().getNoteOrBookBean().getParentId(), new OnNetProcessAdapter<NoteListResBean>() {
                    @Override
                    public void onResult(NoteListResBean resBean) {
                        P().D().setList(resBean);
                        P().U().fillRecycle(P().D().getNoteListData3(resBean.getData()), NoteListFrag.this, NoteListFrag.this);
                    }

                    @Override
                    public void onEnd(int type, String res) {
                        o.finishRefresh();
                    }
                });
            }
        }, this);
    }

    @Override
    public boolean onLongClick(View v) {
        CircleMenuFrag circleMenuFrag = new CircleMenuFrag();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.homeroot, circleMenuFrag);
        transaction.commit();
        circleMenuFrag.setOnFinishListener(new OnFinishListener<Integer>() {
            @Override
            public void onFinish(final Integer m) {
                switch (m) {
                    case 0:
                        P().D().getNoteDataOpe().addNote(P().D().getNoteOrBookBean().getParentId(), new OnNetProcessAdapter() {
                            @Override
                            public void onResult(Object o) {

                            }
                        });

                        break;
                    case 1:
                        P().D().getNoteDataOpe().addNoteBook(0, new OnNetProcessAdapter() {
                            @Override
                            public void onResult(Object o) {
                                P().U().bind.refresh.autoRefresh();
                            }
                        });
                        break;
                }
            }
        });
        return true;
    }

    @Override
    public void onClick(View v) {

        NoteOrBookBean noteOrBookBean = P().D().getList().getData().get((Integer) v.getTag(R.id.position));
        switch (noteOrBookBean.getType()) {
            case NoteOrBookBean.TYPE_NOTE:
                NoteFrag noteFrag = new NoteFrag();
                noteFrag.setArguments(new Bundle());
                noteFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, noteOrBookBean);
                FragmentUtil.getInstance().add(activity, R.id.homeroot, noteFrag);
                break;
            case NoteOrBookBean.TYPE_NOTEBOOK:
                NoteMainFrag noteMainFrag = new NoteMainFrag();
                noteMainFrag.setArguments(new Bundle());
                NoteOrBookBean noteOrBookBean1 = new NoteOrBookBean();
                noteOrBookBean.setParentId(noteOrBookBean.getObjectId());
                noteMainFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, noteOrBookBean);
                FragmentUtil.getInstance().add(activity, R.id.homeroot, noteMainFrag);
                break;
        }
    }
}
