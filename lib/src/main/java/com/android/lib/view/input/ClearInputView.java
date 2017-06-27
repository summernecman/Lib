package com.android.lib.view.input;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.android.lib.R;

import butterknife.ButterKnife;

/**
 * Created by ${viwmox} on 2017-03-01.
 */

public class ClearInputView extends RelativeLayout {

    public ClearInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(LayoutInflater.from(context).inflate(R.layout.layout_input, null));
        ButterKnife.bind(getChildAt(0), this);
    }
}
