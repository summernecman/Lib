package com.android.lib.view.image;

//by summer on 2017-06-15.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.R;
import com.android.lib.base.fragment.BaseFrg;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.NullUtil;
import com.lzy.imagepicker.ImagePicker;

import uk.co.senab.photoview.PhotoView;


public class ImageFrag extends BaseFrg implements View.OnLongClickListener {


    PhotoView photoView;

    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photoView = (PhotoView) view.findViewById(R.id.iv_image);
        if (getArguments() == null || getArguments().getString(ValueConstant.DATA_DATA) == null) {
            return;
        }
        url = getArguments().getString(ValueConstant.DATA_DATA);
        if (!NullUtil.isStrEmpty(url)) {
            ImagePicker.getInstance().getImageLoader().displayImage(getActivity(), url, photoView, 0, 0);
        }
        photoView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
//        if (!url.startsWith("http:")) {
//            ToastUtil.getInstance().showShort(activity, "已经是本地文件");
//            return true;
//        }
//        ToastUtil.getInstance().showShort(activity, "开始保存");
//        BitmapUtil.saveImage(activity, url);
        return true;
    }



}
