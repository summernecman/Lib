package com.android.lib.bean.reqbean;


import com.android.lib.network.bean.req.BaseReqBean;

/**
 * Created by ${viwmox} on 2016-11-16.
 */
public class BaseNurseReqBean extends BaseReqBean {

    /**
     * 获取该指定病区的任务；可忽略，忽略时默认为当前用户所在的病区
     */
    private String rid;

    private String begin;

    private String end;

    private String zyh;

    private String patientid;

    private String regionid;


    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
