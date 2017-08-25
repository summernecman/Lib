package com.summer.desktop.module.note.notelist;

//by summer on 2017-07-28.

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.netadapter.OnNetProcessAdapter;
import com.android.lib.util.FragmentUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.summer.desktop.R;
import com.summer.desktop.module.circlemenu.CircleMenuFrag;
import com.summer.desktop.module.note.bean.NoteListResBean;
import com.summer.desktop.module.note.bean.NoteOrBookBean;
import com.summer.desktop.module.note.notemain.NoteMainFrag;
import com.summer.desktop.module.rename.RenameFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteListFrag extends BaseUIFrag<NoteListUIOpe, NoteListDAOpe> implements ViewListener {

    @Override
    public void doThing() {

        getP().getD().setNoteOrBooks((ArrayList<NoteOrBookBean>) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().setPosition(getArguments().getInt(ValueConstant.DATA_POSITION));
        getP().getU().initRefresh(new OnFinishListener<MaterialRefreshLayout>() {
            @Override
            public void onFinish(final MaterialRefreshLayout o) {
                getP().getD().getNoteDataOpe().getNoteList(getP().getD().getNoteOrBookBean().getObjectId(), new OnNetProcessAdapter<NoteListResBean>() {
                    @Override
                    public void onResult(NoteListResBean resBean) {
                        getP().getD().setList(resBean);
                        getP().getU().fillRecycle(getP().getD().getNoteListData3(resBean.getData()), NoteListFrag.this);
                    }

                    @Override
                    public void onEnd(int type, String res) {
                        o.finishRefreshingDelay();
                    }
                });
            }
        }, this);
        getP().getU().bind.refresh.updateListener();
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                NoteMainFrag noteMainFrag = new NoteMainFrag();
                noteMainFrag.setArguments(new Bundle());
                noteMainFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getList().getData());
                noteMainFrag.getArguments().putSerializable(ValueConstant.DATA_POSITION, (Serializable) v.getTag(R.id.position));
                FragmentUtil.getInstance().add(activity, R.id.homeroot, noteMainFrag);
                break;
            case ViewListener.TYPE_ONLONGCLICK:
                LogUtil.E("longclick");
                final Object o = v.getTag(R.id.position);
                CircleMenuFrag circleMenuFrag = new CircleMenuFrag();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.homeroot, circleMenuFrag);
                transaction.commit();
                circleMenuFrag.setOnFinishListener(new OnFinishListener<Integer>() {
                    @Override
                    public void onFinish(final Integer m) {
                        switch (m) {
                            case 0:
                                getP().getD().getNoteDataOpe().addNote(getP().getD().getNoteOrBookBean().getObjectId(), new OnNetProcessAdapter() {
                                    @Override
                                    public void onResult(Object o) {
                                        getP().getU().bind.refresh.autoRefresh();
                                    }
                                });

                                break;
                            case 1:
                                getP().getD().getNoteDataOpe().addNoteBook(getP().getD().getNoteOrBookBean().getObjectId(), new OnNetProcessAdapter() {
                                    @Override
                                    public void onResult(Object o) {
                                        getP().getU().bind.refresh.autoRefresh();
                                    }
                                });
                                break;
                            case 2:
                                if (o == null) {
                                    return;
                                }
                                int position = (int) o;
                                getP().getD().getNoteDataOpe().delteNote(getP().getD().getList().getData().get(position), new OnNetProcessAdapter() {
                                    @Override
                                    public void onResult(Object o) {
                                        getP().getU().bind.refresh.autoRefresh();
                                    }
                                });
                                break;
                            case 3:
                                if (o == null) {
                                    return;
                                }
                                final int position1 = (int) o;
                                RenameFrag renameFrag = new RenameFrag();
                                FragmentUtil.getInstance().add(activity, R.id.homeroot, renameFrag);
                                renameFrag.setOnfinish(new OnFinishListener() {
                                    @Override
                                    public void onFinish(Object o) {
                                        String s = (String) o;
                                        if (NullUtil.isStrEmpty(s)) {
                                            return;
                                        }
                                        getP().getD().getList().getData().get(position1).setName(s);
                                        getP().getD().getNoteDataOpe().renameNote(getP().getD().getList().getData().get(position1), new OnNetProcessAdapter() {
                                            @Override
                                            public void onResult(Object o) {
                                                getP().getU().bind.refresh.autoRefresh();
                                            }
                                        });
                                    }
                                });

                                break;
                            case 4:
                                getP().getD().getNoteDataOpe().addGalleryNote(getP().getD().getNoteOrBookBean().getObjectId(), new OnNetProcessAdapter() {
                                    @Override
                                    public void onResult(Object o) {
                                        getP().getU().bind.refresh.autoRefresh();
                                    }
                                });

                                break;
                        }
                    }
                });
                break;
        }
    }
}
