package com.summer.desktop.module.note.notedetail;

//by summer on 2017-06-06.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.bean.uibean.TxtFragUIBean;
import com.summer.desktop.util.ViewCreater;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseUIOpe;

public class NoteDetailUIOpe extends BaseUIOpe<TxtFragUIBean> {


    public NoteDetailUIOpe(Context context) {
        super(context, new TxtFragUIBean(context, null));
    }


    public void getData(final Fragment fragment, GsonNoteBean bean, OnFinishListener onFinishListener, View.OnClickListener listener) {
        if (getUiBean().getTxtroot().getChildCount() != 0) {
            RecyclerView recyclerView = (RecyclerView) getUiBean().getTxtroot().getChildAt(0);
            ((NoteDetailAdapter) recyclerView.getAdapter()).notifyDataSetChanged();
        } else {
            RecyclerView recyclerView = (RecyclerView) ViewCreater.create(context, bean);
            getUiBean().getTxtroot().addView(recyclerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((NoteDetailAdapter) recyclerView.getAdapter()).setOnFinishListener(onFinishListener);
            ((NoteDetailAdapter) recyclerView.getAdapter()).setOnClickListener(listener);
        }
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
