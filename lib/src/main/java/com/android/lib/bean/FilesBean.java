package com.android.lib.bean;

//by summer on 2017-08-01.

import java.util.ArrayList;

public class FilesBean extends BaseBean {

    private ArrayList<FileBean> data = new ArrayList<>();

    private int size;

    public ArrayList<FileBean> getData() {
        return data;
    }

    public void setData(ArrayList<FileBean> data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
