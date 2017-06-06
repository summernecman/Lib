package com.summer.lib.view.textview;

//by summer on 2017-05-31.

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;

public class YRTextView extends EditText {

    public YRTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface mtypeface = Typeface.createFromAsset(context.getAssets(), "yuanrui.ttf");
        setTypeface(mtypeface);
    }
}
