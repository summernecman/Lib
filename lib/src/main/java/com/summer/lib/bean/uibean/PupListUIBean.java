package com.summer.lib.bean.uibean;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.summer.lib.R;

/**
 * Created by ${viwmox} on 2016-11-14.
 */
public class PupListUIBean extends BaseUIBean {


    TextView textView;


    public PupListUIBean(Context context, View convertView) {
        super(context, convertView);
        textView = (TextView) convertView.findViewById(R.id.txt);
    }

    public TextView getTextView() {
        return textView;
    }
}
