/**
 *
 */
package top.onee.ssm.expand.util;


import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author xuqiang
 */
public class DateTimeUtils {

    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static long getMillis() {
        return System.currentTimeMillis();
    }

    public static int getWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }


    public static int compareDate(String date1, String date2) {
        String dt1 = date1, dt2 = date2;
        if (date1.indexOf("-") == -1 && date1.length() == 8) {
            dt1 = date1.substring(0, 4) + "-";
            dt1 += date1.substring(4, 6) + "-";
            dt1 += date1.substring(6);
        }

        if (date2.indexOf("-") == -1 && date2.length() == 8) {
            dt2 = date2.substring(0, 4) + "-";
            dt2 += date2.substring(4, 6) + "-";
            dt2 += date2.substring(6);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date first, second;

        try {
            first = sdf.parse(dt1);
            second = sdf.parse(dt2);
        } catch (ParseException e) {
            return -1;
        }

        return DateUtils.truncatedCompareTo(first, second, Calendar.MILLISECOND);
    }

    public static int compareTime(String time1, String time2) {
        if (time1.compareTo(time2) > 0)
            return 1;
        else if (time1.equals(time2))
            return 0;

        return -1;
    }

    public static String getSlashDate() {
        return formatDate("yyyy/MM/dd");
    }

    public static String getStripDate() {
        return formatDate("yyyy-MM-dd");
    }

    public static String getDate() {
        return formatDate("yyyyMMdd");
    }

    public static String getTime() {
        return formatDate("HH:mm:ss");
    }

    public static String getUnsignedDateTime() {
        return getDate() + getUnsignedTime();
    }

    public static String getUnsignedDateTime(Date date) {
        return formatDate("yyyyMMdd", date) + formatDate("HH:mm:ss", date).replace(":", "");
    }

    public static String getUnsignedTime() {
        return formatDate("HH:mm:ss").replace(":", "");
    }

    public static String getSlashDateTime() {
        return formatDate("yyyy/MM/dd HH:mm:ss");
    }

    public static String getStripDateTime() {
        return formatDate("yyyy-MM-dd  HH:mm:ss");
    }

    public static String formatDate(String format) {
        return FastDateFormat.getInstance(format).format(new Date());
    }

    public static String formatDate(String format, Date date) {
        return FastDateFormat.getInstance(format).format(date);
    }

    public static String getDayOfWeek() {
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String week = "";

        switch (dayOfWeek) {
            case 1:
                week = "??????";
                break;
            case 2:
                week = "??????";
                break;
            case 3:
                week = "??????";
                break;
            case 4:
                week = "??????";
                break;
            case 5:
                week = "??????";
                break;
            case 6:
                week = "??????";
                break;
            case 7:
                week = "??????";
                break;
        }

        return week;
    }

    /**
     * ?????????Unix?????????
     * @param date
     * @return
     */
    public static long toUnixStamp(Date date) {
        if (null == date) {
            return 0;
        }
        return date.getTime() / 1000;
    }

    /**
     * Unix??????????????????
     * @param unixstamp
     * @return
     */
    public static Date toDate(long unixstamp) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date d = null;
        try {
            d = sf.parse(sf.format(new Date(unixstamp * 1000)));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return d;
    }

    /**
     * ?????????????????? "yyyy-MM-dd hh:mm:ss"??????
     * @param date
     * @return
     */
    public static String toDateFormatString(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_TIME_FORMAT);
        String t_datesString = null;
        try {
            t_datesString = sf.format(date);
        } catch (Exception e) {
            t_datesString = getStripDateTime();
        }
        return t_datesString;
    }

    /**
     * ??????????????????????????????
     * @param date
     * @param format
     * @return
     */
    public static String toDateFormatString(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        String t_datesString = null;
        try {
            t_datesString = sf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t_datesString;
    }

    public static Date getFirstDayOfWeek(String source) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_TIME_FORMAT);
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        Date date = null;
        try {
            date = sf.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }

    public static Date toDate(String org_str, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sf.parse(org_str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * ????????????"yyyy-MM-dd HH:mm:ss"????????????
     *
     * @param date ??????
     * @return ??????
     */
    public static String getSysTimeStr(Date date) {
        if (date == null) {
            return getSysTimeStr();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * ??????????????????"yyyy-MM-dd HH:mm:ss"????????????
     *
     * @return ????????????
     */
    public static String getSysTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * ??????????????????
     *
     * @return
     */
    public static Date getSystemDate() {
        return new Date();
    }

    /**
     * ??????????????????"yyyy-MM-dd"??????
     *
     * @return
     */
    public static String getSysDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * ????????????"yyyy-MM-dd"??????
     *
     * @param paraDate ??????
     * @return ??????
     */
    public static String getSysDateStr(Date paraDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(paraDate);
    }

    /**
     * ??????n?????????"yyyy-MM-dd HH:mm:ss"????????????
     *
     * @param days ????????????
     * @return ???????????????
     */
    public static String getBeforeDaysTime(int days) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -days);
        Date date = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);

    }

    /**
     * ??????"yyyy-MM-dd HH:mm:ss"?????????????????????
     *
     * @param dateStr ????????????
     * @return ??????
     */
    public static Date getDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
