package top.onee.ssm.expand.util;

import org.apache.commons.beanutils.BeanMap;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ConvertUtils {
    private static Date dateMinValue = getDateMinValue();
    private static Date dateMaxValue = getDateMaxValue();

    public static <K, V> Map<K, V> toMap(List<Map<K, V>> list) {
        Map<K, V> map = new HashMap<K, V>();
        for (Map<K, V> item : list)
            map.putAll(item);

        return map;
    }

    public static void toMap(Object obj, Map<String, Object> map) {
        BeanMap beanMap = new BeanMap(obj);
        Iterator<Entry<Object, Object>> iter = beanMap.entryIterator();
        while (iter.hasNext()) {
            Entry<Object, Object> item = iter.next();
            if (item.getKey().toString().endsWith("class"))
                continue;

            if (item.getValue() == null)
                continue;

            map.put(toString(item.getKey()).toLowerCase(), toString(item.getValue()));
        }
    }

    /**
     * urlencode方法
     *
     * @param url    url地址
     * @param encode 编码
     * @return urlencode结果, 出现异常返回空字符串
     */
    public static String urlEncode(String url, String encode) {
        return urlEncode(url, encode, "");
    }

    /**
     * urldecode方法
     *
     * @param url    url地址
     * @param encode 编码
     * @return urldecode结果, 出现异常返回空字符串
     */
    public static String urlDecode(String url, String encode) {
        return urlDecode(url, encode, "");
    }

    /**
     * urlencode方法
     *
     * @param url          url url地址
     * @param encode       encode 编码
     * @param defaultValue 出现异常时的返回值
     * @return urlencode结果
     */
    public static String urlEncode(String url, String encode, String defaultValue) {
        try {
            return URLEncoder.encode(url, encode);
        } catch (Exception ex) {
            // LogWriter.writeErrorLog(ex);
            return defaultValue;
        }
    }

    /**
     * urldecode方法
     *
     * @param url          url url地址
     * @param encode       encode 编码
     * @param defaultValue 出现异常时的返回值
     * @return urldecode结果
     */
    public static String urlDecode(String url, String encode, String defaultValue) {
        try {
            return URLDecoder.decode(url, encode);
        } catch (Exception ex) {
            // LogWriter.writeErrorLog(ex);
            return defaultValue;
        }
    }


    public static String toString(Object o) {
        return toString(o, null);
    }


    public static String toString(Object object, String defaultValue) {
        if (object == null)
            return defaultValue;

        Class<?> typeClass = object.getClass();

        if (String.class.isAssignableFrom(typeClass)) {
            return object.toString();
        } else if (Long.class.isAssignableFrom(typeClass)) {
            return object.toString();
        } else if (Integer.class.isAssignableFrom(typeClass)) {
            return object.toString();
        } else if (Boolean.class.isAssignableFrom(typeClass)) {
            return object.toString();
        } else if (Double.class.isAssignableFrom(typeClass)) {
            return object.toString();
        } else if (Number.class.isAssignableFrom(typeClass)) {
            return object.toString();
        } else if (Date.class.isAssignableFrom(typeClass)) {
            return object.toString();
        } else {
            return defaultValue;
        }
    }

    /**
     * Convert to short.
     *
     * @param o
     * @return
     */
    public static short toShort(Object o) {
        if (o == null)
            return 0;
        try {
            return Short.parseShort(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return 0;
        }
    }

    /**
     * Convert to short. (with default value)
     *
     * @param o
     * @param defaultValue
     * @return
     */
    public static short toShort(Object o, short defaultValue) {
        if (o == null)
            return defaultValue;
        try {
            return Short.parseShort(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return defaultValue;
        }
    }

    /**
     * Convert to int.
     *
     * @param o
     * @return
     */
    public static int toInt(Object o) {
        if (o == null)
            return 0;
        try {
            return Integer.parseInt(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return 0;
        }
    }

    /**
     * Convert to int. (with default value)
     *
     * @param o
     * @param defaultValue
     * @return
     */
    public static int toInt(Object o, int defaultValue) {
        if (o == null)
            return defaultValue;
        try {
            return Integer.parseInt(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return defaultValue;
        }
    }

    /**
     * Convert to long.
     *
     * @param o
     * @return
     */
    public static long toLong(Object o) {
        if (o == null)
            return 0L;
        try {
            return Long.parseLong(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return 0L;
        }
    }

    /**
     * Convert to long. (with default value)
     *
     * @param o
     * @param defaultValue
     * @return
     */
    public static long toLong(Object o, long defaultValue) {
        if (o == null)
            return defaultValue;
        try {
            return Long.parseLong(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return defaultValue;
        }
    }

    /**
     * Convert to float.
     *
     * @param o
     * @return
     */
    public static float toFloat(Object o) {
        if (o == null)
            return 0.0f;
        try {
            return Float.parseFloat(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return 0.0f;
        }
    }

    /**
     * Convert to float. (with default value)
     *
     * @param o
     * @param defaultValue
     * @return
     */
    public static float toFloat(Object o, float defaultValue) {
        if (o == null)
            return defaultValue;
        try {
            return Float.parseFloat(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return defaultValue;
        }
    }

    /**
     * Convert to double.
     *
     * @param o
     * @return
     */
    public static double toDouble(Object o) {
        if (o == null)
            return 0.0d;
        try {
            return Double.parseDouble(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return 0.0d;
        }
    }

    /**
     * Convert to double. (with default value)
     *
     * @param o
     * @param defaultValue
     * @return
     */
    public static double toDouble(Object o, double defaultValue) {
        if (o == null)
            return defaultValue;
        try {
            return Double.parseDouble(o.toString());
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return defaultValue;
        }
    }

    /**
     * Convert to BigDecimal.
     *
     * @param o
     * @return
     */
    public static BigDecimal toBigDecimal(Object o) {
        if (o == null)
            return BigDecimal.ZERO;
        try {
            return BigDecimal.valueOf(toDouble(o));
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return BigDecimal.ZERO;
        }
    }

    /**
     * Convert to BigDecimal. (with default value)
     *
     * @param o
     * @param defaultValue
     * @return
     */
    public static BigDecimal toBigDecimal(Object o, double defaultValue) {
        if (o == null)
            return BigDecimal.valueOf(defaultValue);
        try {
            return BigDecimal.valueOf(toDouble(o));
        } catch (NumberFormatException e) {
            // LogWriter.writeErrorLog(e);
            return BigDecimal.valueOf(defaultValue);
        }
    }

    /**
     * Convert to boolean.
     *
     * @param o
     * @return
     */
    public static boolean toBoolean(Object o) {
        if (o == null)
            return false;

        String s = o.toString();

        return !(s.equals("") || s.equals("0") || s.equalsIgnoreCase("false"));

    }

    /**
     * Convert to Date,only support the format "yyyy-MM-dd HH:mm:ss"
     *
     * @param o
     * @return
     */
    public static Date toDate(Object o) {
        if (o == null)
            return null;

        String s = o.toString();
        Date retValue = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            retValue = sdf.parse(s);
        } catch (ParseException e) {
            // LogWriter.writeErrorLog(e);
        }
        return retValue;
    }

    /**
     * Convert to Date by your support date format string, such as
     * "yyyy-MM-dd HH:mm:ss" "yyyyMMddHHmmss" "yyyy/MM/dd HH:mm:ss" etc.
     *
     * @param o
     * @param dateFormatStr
     * @return
     */
    public static Date toDate(Object o, String dateFormatStr) {
        if (o == null)
            return null;

        String s = o.toString();
        Date retValue = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
            retValue = sdf.parse(s);
        } catch (ParseException e) {
            // LogWriter.writeErrorLog(e);
        } catch (Exception exp) {
            // LogWriter.writeErrorLog(exp);
        }
        return retValue;
    }

    /**
     * format date to string
     *
     * @param date
     * @param dateFormatStr
     * @return
     */
    public static String toDateString(Object date, String dateFormatStr) {
        if (date == null)
            return null;

        String retValue = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
            retValue = sdf.format(date);
        } catch (Exception exp) {
            // LogWriter.writeErrorLog(exp);
        }
        return retValue;
    }

    /**
     * 获取最小时间
     *
     * @return 1900-01-01 00:00:00
     */
    public static Date getDateMinValue() {
        if (dateMinValue == null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.parse("1900-01-01 00:00:00");
            } catch (Exception ex) {
                // LogWriter.writeErrorLog(ex);
                return new Date();
            }
        } else {
            return dateMinValue;
        }
    }

    /**
     * 获取最大时间
     *
     * @return 2099-12-31 00:00:00
     */
    public static Date getDateMaxValue() {
        if (dateMaxValue == null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.parse("2099-12-31 00:00:00");
            } catch (Exception ex) {
                // LogWriter.writeErrorLog(ex);
                return new Date();
            }
        } else {
            return dateMaxValue;
        }
    }

    /**
     * 将Unicode解析成汉字
     *
     * @return
     */
    public static String decodeUnicode(String string) {
        if (string.equals("") || string == null) {
            return null;
        }
        char aChar;
        int len = string.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = string.charAt(x++);
            if (aChar == '\\') {
                aChar = string.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = string.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed      encoding.");
                        }
                    }
                    if (value == 0)
                        outBuffer.append("");
                    else
                        outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    public static void main(String[] args) {
    }

}
