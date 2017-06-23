package com.android.lib.bean.dbbean;

import com.android.lib.bean.BaseBean;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by ${viwmox} on 2016-05-19.
 */
public class BaseDbBean extends BaseBean implements Serializable {


    public static final String ID = "id";


    @DatabaseField(generatedId = true)
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
