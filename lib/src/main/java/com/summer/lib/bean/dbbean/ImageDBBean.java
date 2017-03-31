package com.summer.lib.bean.dbbean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.summer.lib.bean.dbbean.BaseDbBean;

/**
 * Created by ${viwmox} on 2016-10-31.
 */

@DatabaseTable(tableName = "table_imageinfo")
public class ImageDBBean extends BaseDbBean {

    public static final String URI = "URI";
    @DatabaseField(columnName = URI)
    private String uri;

    public static final String W = "W";
    @DatabaseField(columnName = W)
    private int w;

    public static final String H = "H";
    @DatabaseField(columnName = H)
    private int h;


    public ImageDBBean(int w, int h, String uri) {
        this.h = h;
        this.uri = uri;
        this.w = w;
    }

    public ImageDBBean() {
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}
