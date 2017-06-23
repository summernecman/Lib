package com.android.lib.network.bean.db;

import com.android.lib.bean.dbbean.BaseDbBean;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ${viwmox} on 2016-12-13.
 */
@DatabaseTable(tableName = "table_netcache")
public class NetCacheDBBean extends BaseDbBean {


    public static final String USERNAME = "USERNAME";
    public static final String REQ = "REQ";
    public static final String URL = "URL";
    @DatabaseField(columnName = USERNAME)
    private String userName;
    @DatabaseField(columnName = REQ)
    private String req;
    @DatabaseField(columnName = URL)
    private String url;

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
