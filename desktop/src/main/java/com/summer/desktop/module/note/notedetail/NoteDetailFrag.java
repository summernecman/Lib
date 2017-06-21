package com.summer.desktop.module.note.notedetail;

//by summer on 2017-06-06.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.dabean.NoteDetail;
import com.summer.desktop.bean.dabean.TxtNote;
import com.summer.desktop.module.circlemenu.CircleMenuFrag;
import com.summer.lib.base.fragment.BaseUIFrag;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.bean.databean.EventBean;
import com.summer.lib.util.GsonUtil;
import com.summer.lib.util.IntentUtil;

import java.util.ArrayList;


public class NoteDetailFrag extends BaseUIFrag<NoteDetailUIOpe, NoteDetailDAOpe> implements OnFinishListener {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOpes().getDa().init(fragment);
        getOpes().getUi().getData(fragment, getOpes().getDa().bean, this, this);
        getOpes().getUi().init(fragment, getOpes().getDa().bean, new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                switch (index) {
                    case 0:
                        IntentUtil.getInstance().photosShowFromphone(fragment, 0);
                        break;
                    case 1:
                        switch (getOpes().getDa().bean.getType()) {
                            case GsonNoteBean.TYPE_GALLERY:

                                break;
                            default:
                                getOpes().getDa().bean.getData().add(new NoteDetail(NoteDetail.TXT, GsonUtil.getInstance().toJson(new TxtNote("new\\n"))));
                                getOpes().getUi().getData(fragment, getOpes().getDa().bean, NoteDetailFrag.this, NoteDetailFrag.this);
                                break;
                        }
                        break;
                    case 3:
//                        control.dc.bean.getData().add(new NoteDetail(NoteDetail.LINK,gson.toJson(new LinkNote("http://www.baidu.com"))));
//                        control.vc.getData(fragment,control.dc.bean);
                        break;
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getOpes().getDa().initUpade(getOpes().getDa().bean, getOpes().getDa().note);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        getOpes().getDa().onresult(data, getOpes().getDa().bean);
        getOpes().getUi().getData(fragment, getOpes().getDa().bean, this, NoteDetailFrag.this);
    }

    @Override
    public void onFinish(final Object o) {
        final CircleMenuFrag circleMenuFrag = new CircleMenuFrag();
        FragmentTransaction transaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.root, circleMenuFrag);
        transaction.commit();
        circleMenuFrag.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(Object b) {
                getOpes().getDa().dealItemLongClick(fragment, (EventBean) o, (int) b);

            }
        });
    }

    @Override
    public void onClick(View v) {
        getOpes().getUi().goToImagesViewPager(fragment, getOpes().getDa().initImageUrl((ArrayList<NoteDetail>) v.getTag(R.id.data1)), (int) v.getTag(R.id.position));
    }
}
