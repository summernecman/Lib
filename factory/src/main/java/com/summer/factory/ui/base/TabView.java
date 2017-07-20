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

import java.util.ArrayList;

public class TabView extends RadioGroup {

    boolean add = false;

    ArrayList<Txt> txt = new ArrayList<>();


    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (!add) {
            for (int i = 0; i < txt.size(); i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(txt.get(i).txt);
                radioButton.setTextColor(Color.BLACK);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
                radioButton.setBackgroundResource(R.drawable.drawable_tab_select);
                radioButton.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
                radioButton.setTag(R.id.position, i);
                this.addView(radioButton);
            }
            add = true;
            if (txt.size() > 0) {
                ((RadioButton) getChildAt(0)).setChecked(true);
            }
        }
    }

    public ArrayList<Txt> getTxt() {
        return txt;
    }

    public void setTxt(ArrayList<Txt> txt) {
        this.txt = txt;
    }

    public static class Txt {
        public String txt;

        public Txt(String txt) {
            this.txt = txt;
        }
    }

}
