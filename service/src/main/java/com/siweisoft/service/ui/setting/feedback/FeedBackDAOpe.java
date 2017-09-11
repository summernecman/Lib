package com.siweisoft.service.ui.setting.feedback;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.R;

import java.util.ArrayList;

public class FeedBackDAOpe extends BaseDAOpe {

    ArrayList<Object> pics = new ArrayList<>();

    public FeedBackDAOpe(Context context) {
        super(context);
        pics.add(R.drawable.icon_add);
    }

    public ArrayList<Object> getPics() {
        if (pics.size() > 0 && pics.get(pics.size() - 1) instanceof Integer) {

        }
        return pics;
    }

    public void addPic(String str) {
        if (pics.size() > 0 && pics.get(pics.size() - 1) instanceof Integer) {
            pics.remove(pics.size() - 1);
        }
        pics.add(str);
        pics.add(R.drawable.icon_add);
    }

}
