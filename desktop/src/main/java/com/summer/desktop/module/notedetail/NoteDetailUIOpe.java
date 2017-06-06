package com.summer.desktop.module.notedetail;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.uibean.TxtFragUIBean;
import com.summer.desktop.module.boom.BoomFrag;
import com.summer.desktop.util.ViewCreater;
import com.summer.lib.base.ope.BaseUIOpe;

public class NoteDetailUIOpe extends BaseUIOpe<TxtFragUIBean> {


    public NoteDetailUIOpe(Context context) {
        super(context, new TxtFragUIBean(context, null));
    }


    public void getData(final Fragment fragment, GsonNoteBean bean) {
        getUiBean().getTxtroot().removeAllViews();
        getUiBean().getTxtroot().addView(ViewCreater.create(context, bean.getData()), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((NoteDetailAdapter) ((RecyclerView) getUiBean().getTxtroot().getChildAt(0)).getAdapter()).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                final BoomFrag boomFrag = new BoomFrag();
                FragmentTransaction transaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.root, boomFrag);
                transaction.commit();
//                boomFrag.setOnfinish(new Onfinish() {
//                    @Override
//                    public void finished(Object o) {
//                        BoomMsg boomMsg = new BoomMsg();
//                        boomMsg.postion = (int) v.getTag(R.id.position);
//                        boomMsg.noteDetail = (NoteDetail) v.getTag(R.id.data);
//                        EventBus.getDefault().post(boomMsg);
//                    }
//                });
                return true;
            }
        });

    }

    public void init(final Fragment fragment, final GsonNoteBean bean, OnBMClickListener onBMClickListener) {
        String[] strings = new String[]{"图片", "文字", "文件", "链接", "", "", "", "", ""};
        for (int i = 0; i < getUiBean().getBmb().getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalText(strings[i])
                    .listener(onBMClickListener);
            builder.rotateText(true);
            builder.rotateImage(true);
            getUiBean().getBmb().addBuilder(builder);
        }
    }
}
