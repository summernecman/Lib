package com.summer.factory.ui.base;

//by summer on 2017-07-17.

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.summer.factory.R;

public class TabView extends RadioGroup {

    int num = 2;                                                                                    //tab数量
    boolean add = false;


    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (!add) {
            for (int i = 0; i < num; i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText("fdsf");
                radioButton.setTextColor(Color.BLACK);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
                radioButton.setBackgroundResource(R.drawable.drawable_tab_select);
                radioButton.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
                radioButton.setTag(R.id.position, i);
                this.addView(radioButton);
            }
            add = true;
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
