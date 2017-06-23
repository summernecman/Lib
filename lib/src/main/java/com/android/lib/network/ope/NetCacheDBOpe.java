package com.android.lib.network.ope;

import android.content.Context;

import com.android.lib.base.ope.BaseDBOpe;
import com.android.lib.network.bean.db.NetCacheDBBean;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-12-13.
 */
public class NetCacheDBOpe extends BaseDBOpe<NetCacheDBBean> {


    public NetCacheDBOpe(Context context, NetCacheDBBean netCacheDBBean) {
        super(context, netCacheDBBean);
    }


    public ArrayList<String> getReq(String name) {
        QueryBuilder queryBuilder = daoOpe.queryBuilder();
        ArrayList<String> strings = new ArrayList<>();
        Where where = queryBuilder.where();
        try {
            where.eq(NetCacheDBBean.USERNAME, name);

            ArrayList<String> s = (ArrayList<String>) queryBuilder.query();
            if (s != null && s.size() != 0) {
                strings.addAll(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public void save(String username, String req, String url) {
        NetCacheDBBean netCacheDBBean = new NetCacheDBBean();
        netCacheDBBean.setReq(req);
        netCacheDBBean.setUserName(username);
        netCacheDBBean.setUrl(url);
        try {
            daoOpe.create(netCacheDBBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
