package com.android.lib.view.bottomdialogmenuview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.view.other.SweetView;

import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-10-08.
 */
public class BottomDialogMenuView extends LinearLayout {


    SweetView sweetView;

    public BottomDialogMenuView(Context context, ArrayList<String> names) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_app, null);
        addView(view);
        sweetView = (SweetView) view.findViewById(R.id.sv_sv);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.root);
        for (int i = 0; i < names.size(); i++) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_textview, null);
            TextView textView = (TextView) view1.findViewById(R.id.tv_txt);
            textView.setText(names.get(i));
            viewGroup.addView(view1);
        }
    }

    public BottomDialogMenuView(Context context, String[] names) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_app, null);
        addView(view);
        sweetView = (SweetView) view.findViewById(R.id.sv_sv);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.root);
        for (int i = 0; i < names.length; i++) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_textview, null);
            TextView textView = (TextView) view1.findViewById(R.id.tv_txt);
            textView.setText(names[i]);
            viewGroup.addView(view1);
        }
    }

    public BottomDialogMenuView(Context context, String[] names, OnClickListener onClickListener) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_app, null);
        addView(view);
        sweetView = (SweetView) view.findViewById(R.id.sv_sv);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.root);
        for (int i = 0; i < names.length; i++) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_textview, null);
            TextView textView = (TextView) view1.findViewById(R.id.tv_txt);
            textView.setText(names[i]);
            textView.setTag(R.id.position, i);
            textView.setOnClickListener(onClickListener);
            viewGroup.addView(view1);
        }
    }

    public SweetView getSweetView() {
        return sweetView;
    }
}
