package com.android.lib.view.textview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ${viwmox} on 2017-02-21.
 */

public class AppEditText extends EditText implements View.OnFocusChangeListener {

    Context context;

    String subTxt = "";

    int type = InputType.TYPE_NULL;


    public AppEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setInputType(type);
            setText(getText().toString().replace(subTxt, ""));
        } else {
            setInputType(InputType.TYPE_NULL);
            setText(getText().toString() + subTxt);
        }
    }

    public String getSubTxt() {
        return subTxt;
    }

    public void setSubTxt(String subTxt) {
        this.subTxt = subTxt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                this.callOnClick();
                break;
        }
        return super.onTouchEvent(event);
    }
}
