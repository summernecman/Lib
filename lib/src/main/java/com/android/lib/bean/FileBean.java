package com.android.lib.bean;

//by summer on 2017-08-01.

import java.io.File;

public class FileBean extends BaseBean {

    private File file;

    public FileBean(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
