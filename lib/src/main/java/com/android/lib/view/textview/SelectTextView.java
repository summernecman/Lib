package com.android.lib.view.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lib.R;

/**
 * Created by ${viwmox} on 2016-10-18.
 */
public class SelectTextView extends TextView {


    public SelectTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            setTextColor(getResources().getColor(R.color.red));
        } else {
            setTextColor(getResources().getColor(R.color.white));
        }
        if (getParent() != null) {
            ViewGroup group = (ViewGroup) getParent();
            if (group.isSelected()) {
                setTextColor(getResources().getColor(R.color.red));
            } else {
                setTextColor(getResources().getColor(R.color.white));
            }
        }
    }
}
