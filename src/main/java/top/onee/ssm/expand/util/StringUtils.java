package top.onee.ssm.expand.util;

import org.apache.commons.lang.text.StrBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类。
 *
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public abstract class StringUtils {

    private static final String FORMAT_SPECIFIER = "\\{\\d+\\}";

    private static final String DATE_FORMAT_SPECIFIER = "\\{\\d+\\:[\\w\\s\\:\\-\\/\\.]+\\}";

    private static Pattern fsPattern = Pattern.compile(FORMAT_SPECIFIER);

    private static Pattern dfsPattern = Pattern.compile(DATE_FORMAT_SPECIFIER);

    private StringUtils() {
    }

    /**
     * format string <br/>
     * example:StringFormat.format("a={0},b={1:yyyy-MM-dd HH:mm:ss.SSS},c={2}",
     * "a", new Date(), "c"))
     *
     * @param f
     * @param args 参数
     * @return
     */
    public static String format(String f, Object... args) {
        if (null != f && null != args) {
            String result = doFormat(f, args);
            return doDateFormat(result, args);
        } else {
            return f;
        }
    }

    public static String format2(String f, Object... args) {
        return MessageFormat.format(f, args);
    }

    /**
     * <p>
     * Checks if a String is not empty ("") and not null.
     * </p>
     * <p>
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     * <p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param f
     * @param args
     * @return
     */
    private static String doFormat(String f, Object... args) {
        String result = f;
        Matcher matcher = fsPattern.matcher(result);
        while (matcher.find()) {
            String m_g = matcher.group();
            int f_index = ConvertUtils.toInt(m_g.substring(1, m_g.length() - 1));

            result = result.substring(0, matcher.start()) + ConvertUtils.toString(args[f_index]) + result.substring(matcher.end(), result.length());

            matcher = fsPattern.matcher(result);
        }

        return result;
    }

    /**
     * @param f
     * @param args
     * @return
     */
    private static String doDateFormat(String f, Object... args) {
        String result = f;
        Matcher matcher = dfsPattern.matcher(result);
        while (matcher.find()) {
            String m_g = matcher.group();
            int special_char_index = m_g.indexOf(":");
            int f_index = ConvertUtils.toInt(m_g.substring(1, special_char_index));
            String dateFormat = m_g.substring(special_char_index + 1, m_g.length() - 1);

            result = result.substring(0, matcher.start()) + ConvertUtils.toDateString(args[f_index], dateFormat) + result.substring(matcher.end(), result.length());

            matcher = dfsPattern.matcher(result);
        }

        return result;
    }

    /**
     * compare string.<br>
     * example:<br>
     * a=null b=null return:true<br>
     * a="e" b=null return:false<br>
     * a="e" b="E" return:false<br>
     * a="e" b="e" return:true<br>
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean compare(String source, String target) {
        return source == null ? target == null : source.equals(target);
    }

    /**
     * compare string.<br>
     *
     * @param source
     * @param target
     * @param ignoreCase
     * @return
     */
    public static boolean compare(String source, String target, boolean ignoreCase) {
        return source == null ? target == null : ignoreCase ? source.equalsIgnoreCase(target) : source.equals(target);
    }

    /**
     * 大写首字符
     *
     * @param str 待处理字符串
     * @return 新字符串
     */
    public static String upperCaseFirstLetter(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StrBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * 字符是否忽略大小写相等
     *
     * @param left
     * @param right
     * @return TRUE: 相等
     */
    public static boolean isStrEgnoreCaseEqual(String left, String right) {
        return left == null ? right == null : left.equalsIgnoreCase(right);
    }

    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     *
     * @param value 待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     *
     * @param obj
     * @return
     */
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1)
            return false;

        int i = 0;
        if (length > 1 && chars[0] == '-')
            i = 1;

        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串列表是否不为空。
     *
     * @param values
     * @return
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     *
     * @param unicode
     * @return
     */
    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); i++) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }

    /**
     * 过滤不可见字符
     *
     * @param input
     * @return
     */
    public static String stripNonValidXMLCharacters(String input) {
        if (input == null || ("".equals(input)))
            return "";
        StringBuilder out = new StringBuilder();
        char current;
        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF)) || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }

    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取字符串输入流
     *
     * @param sInputString
     * @return
     */
    public static InputStream getStringStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
        }
        return tInputStringStream;
    }

    /**
     * InputStream 转 String
     *
     * @param inputStream
     * @return
     */
    public static String InputStreamToString(InputStream inputStream) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        try {
            for (int n; (n = inputStream.read(buffer)) != -1; ) {
                out.write(buffer, 0, n);
            }
            out.close();
            inputStream.close();
            return new String(out.toByteArray(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成流水号
     *
     * @return
     */
    public static String generateFlowId() {
        String curentTime = DateTimeUtils.toDateFormatString(new Date(), "yyyyMMddHHmmss");
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return curentTime + sb.toString();
    }
}
