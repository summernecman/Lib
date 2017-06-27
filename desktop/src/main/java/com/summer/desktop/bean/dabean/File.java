package com.summer.desktop.bean.dabean;

//by summer on 2017-05-26.


import com.android.lib.bean.databean.BaseDABean;

public class File extends BaseDABean {

    private String type;

    private String file;

    public File() {
    }

    public File(String type, String file) {
        this.type = type;
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
