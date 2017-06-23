package com.android.lib.base.interf;

import android.view.View;

/**
 * Created by ${viwmox} on 2016-05-19.
 */
public class OnClickAdapter implements View.OnClickListener {

    private View view;

    public OnClickAdapter(View view) {
        this.view = view;
    }

    @Override
    public void onClick(View v) {

    }

    public void onClickAdapter(View v) {
        onClick(v);
    }

    public View getView() {
        return view;
    }
}
