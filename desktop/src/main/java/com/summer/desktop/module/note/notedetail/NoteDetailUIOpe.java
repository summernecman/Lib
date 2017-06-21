package com.summer.desktop.module.note.notedetail;

//by summer on 2017-06-06.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.summer.desktop.R;
import com.summer.desktop.bean.dabean.GsonNoteBean;
import com.summer.desktop.databinding.FragNoteTxtBinding;
import com.summer.desktop.util.ViewCreater;
import com.summer.lib.base.interf.OnFinishListener;
import com.summer.lib.base.ope.BaseUIBean;
import com.summer.lib.constant.ValueConstant;
import com.summer.lib.util.FragmentUtil;
import com.summer.lib.view.image.imagepager.ImagePagerFrag;

import java.util.ArrayList;

public class NoteDetailUIOpe extends BaseUIBean<FragNoteTxtBinding> {


    public NoteDetailUIOpe(Context context) {
        super(context);
    }

    public void getData(final Fragment fragment, GsonNoteBean bean, OnFinishListener onFinishListener, View.OnClickListener listener) {
        if (viewDataBinding.txtroot.getChildCount() != 0) {
            RecyclerView recyclerView = (RecyclerView) viewDataBinding.txtroot.getChildAt(0);
            (recyclerView.getAdapter()).notifyDataSetChanged();
        } else {
            RecyclerView recyclerView = (RecyclerView) ViewCreater.create(context, bean);
            viewDataBinding.txtroot.addView(recyclerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((NoteDetailAdapter) recyclerView.getAdapter()).setOnFinishListener(onFinishListener);
            ((NoteDetailAdapter) recyclerView.getAdapter()).setOnClickListener(listener);
        }
    }

    public void init(final Fragment fragment, final GsonNoteBean bean, OnBMClickListener onBMClickListener) {
        String[] strings = new String[]{"图片", "文字", "文件", "链接", "", "", "", "", ""};
        for (int i = 0; i < viewDataBinding.bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalText(strings[i])
                    .listener(onBMClickListener);
            builder.rotateText(true);
            builder.rotateImage(true);
            viewDataBinding.bmb.addBuilder(builder);
        }
    }

    public void goToImagesViewPager(Fragment fragment, ArrayList<String> stringsm, int p) {
        ImagePagerFrag pagerFrag = new ImagePagerFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ValueConstant.DATA_DATA, stringsm);
        bundle.putSerializable(ValueConstant.DATA_POSITION, p);
        pagerFrag.setArguments(bundle);
        FragmentUtil.getInstance().add(fragment.getActivity(), R.id.root_note, pagerFrag);
    }
}
