package com.android.lib.util.data;

import android.annotation.SuppressLint;

import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {

    public static final String YYYY__MM__DD__HH__MM__SS = "yyyMMddHHmmss";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";

    public static final String MM_DD_HH_MM = "MM-dd HH:mm";

    public static final String DD_HH_MM_SS = "dd HH:mm:ss";

    public static final String HH_MM_SS = "HH:mm:ss";

    public static final String MM_SS = "mm:ss";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YEAR = "yyyy";
    public static final String MONTH = "MM";
    public static final String DAY = "dd";
    public static final String HOUR = "HH";
    public static final String MINUTE = "mm";
    public static final String SECOND = "ss";


    public static String getNowStr(String dateFormat) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    public static String getdDateStr(String dateFormat, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }


    public static String getdDateStr(String dateFormat, String dateFormat2, String data) {
        if (NullUtil.isStrEmpty(data)) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = format.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat(dateFormat2);
        return format2.format(date);
    }

    public static Date getStrDate(String time, String dateFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.parse(time);
    }

    public static String getbefore7TimeYYYYMMdd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day - 7);
        return format.format(calendar.getTime());
    }

    public static String getTomorromTimeYYYYMMdd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day + 1);
        return format.format(calendar.getTime());
    }

    public static int getnowMinTotal() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60 + Calendar.getInstance().get(Calendar.MINUTE);
    }


    public static String getTimeDistance(String startTime, String endTime) {
        try {
            LogUtil.E(startTime + "&&" + endTime);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat timeformat = new SimpleDateFormat("H:mm:ss");
            Date startDate = format.parse(startTime);
            Date endDate = format.parse(endTime);
            long between = (endDate.getTime() - startDate.getTime()) / 1000;
            long day1 = between / (24 * 3600);

            long hour1 = between % (24 * 3600) / 3600;

            long minute1 = between % 3600 / 60;
            return day1 + "天" + hour1 + "时";
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTimeDistance(String endTime) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date endDate = format.parse(endTime);
            long between = (endDate.getTime() - System.currentTimeMillis()) / 1000;
            long day1 = between / (24 * 3600);

            long hour1 = between % (24 * 3600) / 3600;

            long minute1 = between % 3600 / 60;
            if (between >= 0) {
                return day1 + "天" + hour1 + "时";
            } else {
                return "0天0时";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatDateTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (time == null || "".equals(time)) {
            return "";
        }
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar current = Calendar.getInstance();

        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();    //昨天
        Calendar tomorrow = Calendar.getInstance();    //明天
        Calendar afterTomorrow = Calendar.getInstance();    //明天+1

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);


        tomorrow.set(Calendar.YEAR, current.get(Calendar.YEAR));
        tomorrow.set(Calendar.MONTH, current.get(Calendar.MONTH));
        tomorrow.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) + 1);
        tomorrow.set(Calendar.HOUR_OF_DAY, 0);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);

        afterTomorrow.set(Calendar.YEAR, current.get(Calendar.YEAR));
        afterTomorrow.set(Calendar.MONTH, current.get(Calendar.MONTH));
        afterTomorrow.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) + 2);
        afterTomorrow.set(Calendar.HOUR_OF_DAY, 0);
        afterTomorrow.set(Calendar.MINUTE, 0);
        afterTomorrow.set(Calendar.SECOND, 0);

        current.setTime(date);

        if (current.after(today) && current.before(tomorrow)) {
            return "今天";
        } else if (current.before(today) && current.after(yesterday)) {

            return "昨天";
        } else if (current.after(tomorrow) && current.before(afterTomorrow)) {
            return "明天";
        } else {
            return "号";
        }
    }

    public static int getHours() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getMin() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static int getSec() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public static int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    public static String getYYYYMMDD(int year, int month, int day) {
        String y = year + "";
        String m = (month + 1) + "";
        String d = day + "";

        if ((month + 1) < 10) {
            m = "0" + m;
        }
        if (day < 10) {
            d = "0" + day;
        }
        return y + "-" + m + "-" + d;
    }


    public static String getYYYYMMDDHHMM(int year, int month, int day, int hour, int min) {
        String y = year + "";
        String m = (month + 1) + "";
        String d = day + "";
        String h = hour + "";
        String mm = min + "";

        if (month < 10) {
            m = "0" + month;
        }
        if (day < 10) {
            d = "0" + day;
        }
        if (hour < 10) {
            h = "0" + hour;
        }
        if (min < 10) {
            mm = "0" + min;
        }
        return y + "-" + m + "-" + d + "  " + h + ":" + mm;
    }

    public static boolean isTodayMMDDHHMM(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            Calendar calendar = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            calendar.setTime(date);
            if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                    && calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                    && calendar.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isTodayMMDDHHMM(int year, int month, int day) {
        Calendar today = Calendar.getInstance();
        return year == today.get(Calendar.YEAR)
                && month == today.get(Calendar.MONTH)
                && day == today.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(long timemillins) {
        Date date = new Date(timemillins);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(long timemillins) {
        Date date = new Date(timemillins);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    @SuppressLint("SimpleDateFormat")
    public static String yyyyMMdd_HHmmss_to_yyyyMMdd_HHmm(String strD) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(strD);
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static ArrayList<int[]> getTimeLevels(int sh, int sm, int eh, int em) {
        ArrayList<int[]> floats = new ArrayList<>();
        if ((sh * 60 + sm) > (eh * 60 + em)) {//跨0
            eh += 24;
        }
        int level = eh - sh + 1;
        switch (level) {
            case 1:
                floats.add(new int[]{sm, em});
                break;
            case 2:
                floats.add(new int[]{sm, 60});
                floats.add(new int[]{0, em});
                break;
            default:
                floats.add(new int[]{sm, 60});
                for (int i = (sh + 1); i < eh; i++) {
                    floats.add(new int[]{0, 60});
                }
                floats.add(new int[]{0, em});
                break;
        }
        return floats;
    }

}
