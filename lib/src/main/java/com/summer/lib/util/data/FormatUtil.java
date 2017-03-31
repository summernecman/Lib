package com.summer.lib.util.data;

/**
 * Created by ${viwmox} on 2016-05-05.
 */
public class FormatUtil {

    private static FormatUtil instance;

    public static FormatUtil getInstance() {
        if (instance == null) {
            instance = new FormatUtil();
        }
        return instance;
    }

    public float getFloat(String str) {

        if (str == null) {
            return 0f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0f;
    }

    public int getInt(String str) {

        if (str == null) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getYYMMDD(int y, int m, int d) {
        //SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String yy, mm, dd;
        yy = y + "";
        mm = m < 10 ? "0" + m : m + "";
        dd = d < 10 ? "0" + d : d + "";
        return yy + "-" + mm + "-" + dd;
    }

    public String getHHMM(int h, int m) {
        //SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String hh, mm;
        hh = h + "";
        mm = m + "";
        if (h < 10) {
            hh = "0" + h;
        }
        if (m < 10) {
            mm = "0" + m;
        }
        return hh + ":" + mm;
    }
}
