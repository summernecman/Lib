package com.summer.desktop.bean.uibean;

//by summer on 2017-06-06.

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.summer.desktop.R;
import com.summer.lib.bean.uibean.BaseUIBean;

import butterknife.BindView;

public class NoteItemUIBean extends BaseUIBean {


    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.date)
    TextView date;

    public NoteItemUIBean(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_note);
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getTextView() {
        return textView;
    }

    public TextView getDate() {
        return date;
    }
}
