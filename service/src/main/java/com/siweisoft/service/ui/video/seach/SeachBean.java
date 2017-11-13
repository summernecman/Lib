package com.siweisoft.service.ui.video.seach;

//by summer on 2017-11-10.

import com.android.lib.bean.BaseBean;
import com.siweisoft.service.netdb.videotip.VideoTipBean;

import java.util.ArrayList;

public class SeachBean extends BaseBean {

    private String txt;

    private ArrayList<VideoTipBean> data = new ArrayList<>();

    private boolean can = false;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public ArrayList<VideoTipBean> getData() {
        return data;
    }

    public void setData(ArrayList<VideoTipBean> data) {
        this.data = data;
    }

    public boolean isCan() {
        return can;
    }

    public void setCan(boolean can) {
        this.can = can;
    }

    public ArrayList<String> getType() {
        ArrayList<String> type = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                type.add(data.get(i).getType());
            }
        }
        return type;
    }
}
