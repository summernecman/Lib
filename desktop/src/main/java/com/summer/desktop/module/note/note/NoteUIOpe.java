package com.summer.desktop.module.note.note;

//by summer on 2017-07-31.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.LayoutDABean;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil;
import com.android.lib.view.image.imagepager.ImagePagerFrag;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.summer.desktop.R;
import com.summer.desktop.databinding.FragNoteDetailBinding;
import com.summer.desktop.module.note.note.bean.NoteBean;

import java.util.ArrayList;

public class NoteUIOpe extends BaseUIOpe<FragNoteDetailBinding> {


    public NoteUIOpe(Context context) {
        super(context);
    }


    public void fillNoteDetail(ArrayList<LayoutDABean> data, int type, ViewListener listener) {
        if (type == NoteBean.NOTE_TYPE_GALLERY) {
            bind.recycle.setLayoutManager(new GridLayoutManager(context, 3));
        } else {
            bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        }
        NoteAdapter noteAdapter = new NoteAdapter(context, data);
        bind.recycle.setAdapter(noteAdapter);
        noteAdapter.setViewListener(listener);
    }

    public void init(OnBMClickListener onBMClickListener) {
        String[] strings = new String[]{"图片", "文字", "文件", "链接", "", "", "", "", ""};
        for (int i = 0; i < bind.bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalText(strings[i])
                    .listener(onBMClickListener);
            builder.rotateText(true);
            builder.rotateImage(true);
            bind.bmb.addBuilder(builder);
        }
    }

    public void goToImagesViewPager(Fragment fragment, ArrayList<String> stringsm, int p) {
        ImagePagerFrag pagerFrag = new ImagePagerFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ValueConstant.DATA_DATA, stringsm);
        bundle.putSerializable(ValueConstant.DATA_POSITION, p);
        pagerFrag.setArguments(bundle);
        FragmentUtil.getInstance().add(fragment.getActivity(), R.id.homeroot, pagerFrag);
    }


}
