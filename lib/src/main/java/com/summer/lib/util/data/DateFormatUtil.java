package com.summer.lib.util.data;

import android.annotation.SuppressLint;

import com.summer.lib.util.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {


    public static String getNowyyyyMMddHHmmss() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String getTodayYYYYMMDD() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getTodayYearYYYY() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy年");
        return format.format(date);
    }

    public static String getTodayMonYYYY() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("MM月");
        return format.format(date);
    }

    public static String getTodayYYYYMMDD2() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }


    /**
     * 把日期转换成年月日格式
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String convent_yyyyMMdd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 年月日小时没有空格
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String convent_yyyyMMddHH(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
        return format.format(date);
    }

    public static String convent_MMddHHMM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(" MM-dd HH:mm");
        return format.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String convent_yyyyMMddHHMM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    /**
     * 把日期转黄成月日格式
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String convent_MMdd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(date);
    }

    /**
     * 把日期转换成时分格式
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String convent_HHmm(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    /**
     * 把日期转换成年月日时分秒格式
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String convent_yyyyMMddHHmmss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 年月日时分秒没有分隔�?
     *
     * @param date
     * @return
     */
    public static String convent_YYYYMMddHHmmss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    /**
     * 把日期转换成时分秒格式
     *
     * @param date
     * @return
     */
    public static String convent_HHmmss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        return format.format(date);
    }


    public static String convent_YYYYMMDD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 把日期转换成24时分秒格式
     *
     * @param date
     * @return
     */
    public static String convent_HH24mmss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    /**
     * yyyy-MM-dd to yyyy-MM-dd 00:00:00
     *
     * @throws ParseException
     */
    public static Date convent_yyyyMMdd000000(String strD)
            throws ParseException {
        strD = strD.trim();
        strD += " 00:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(strD);
    }

    /**
     * 把yyyy-MM-dd HH:mm:ss格式的字符串转换为日期
     *
     * @param strD
     * @return
     * @throws ParseException
     */
    public static Date convent_yyyyMMddHHmmss(String strD)
            throws ParseException {
        strD = strD.trim();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(strD);
    }

    public static Calendar convent_yyyyMMddHHmmssCalendar(String strD) throws ParseException {
        strD = strD.trim();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(strD));
        return calendar;
    }


    public static String getnowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public static String getnowTimeYYYYMMdd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static String getnowTimeHHmm() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date());
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

    /**
     * 把yyyy-MM-dd HH:mm格式的字符串转换为日期
     *
     * @param strD
     * @return
     * @throws ParseException
     */
    @SuppressLint("SimpleDateFormat")
    public static Date convent_yyyyMMddHHmm(String strD) throws ParseException {
        strD = strD.trim();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.parse(strD);
    }

    /**
     * 把yyyy-MM-dd格式的字符串转换为日期
     *
     * @param strD
     * @return
     * @throws ParseException
     */
    @SuppressLint("SimpleDateFormat")
    public static Date convent_yyyyMMdd(String strD) throws ParseException {
        strD = strD.trim();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(strD);
    }

    /**
     * 把yyyyMMddHHmmss格式的字符串转换为日期
     *
     * @param strD
     * @return
     * @throws ParseException
     */
    public static Date convent_yyyyMMddHHmmss_nospilt(String strD)
            throws ParseException {
        strD = strD.trim();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.parse(strD);
    }

    /**
     * 把yyyy-MM-dd HH:mm格式的字符串转换为字符串HH:mm
     *
     * @param strD
     * @return
     * @throws ParseException
     */
    @SuppressLint("SimpleDateFormat")
    public static String convent_HHmm(String strD) throws ParseException {
        strD = strD.trim();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = format.parse(strD);
        format = new SimpleDateFormat("HH:mm");
        String str = format.format(date);
        return str;
    }

    /**
     * 把yyyy-MM-dd HH:mm:ss格式的字符串转换为字符串yyyy-MM-dd
     */
    public static String convent_ddHHmmstr(String str) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("d日H点m分");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 把yyyy-MM-dd HH:mm:ss格式的字符串转换为字符串yyyy-MM-dd
     */
    public static String convent_YYYYMMDDStr(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("yyyy年M月d日");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 把yyyy-MM-dd HH:mm:ss格式的字符串转换为字符串yyyy-MM-dd
     */
    public static String convent_YYYYMMDD2Str(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("yyyy/M/d");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String convent_YYYY_MM_DDStr(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("yyyy-M-d");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 把yyyy-MM-dd HH:mm:ss格式的字符串转换为字符串yyyy-MM-dd
     */
    public static String convent_MMStr(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("M");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 把yyyy-MM-dd HH:mm:ss格式的字符串转换为字符串yyyy-MM-dd
     */
    public static String convent_DDStr(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("d");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
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
            return convent_DDStr(time) + "号";
        }
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

    public static String getMMDDHHMM(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("MM-dd HH:mm");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getYYYYMMdd(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
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


    public static String convent_MMDDStr(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(str);
            format = new SimpleDateFormat("M月d日");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
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
}
