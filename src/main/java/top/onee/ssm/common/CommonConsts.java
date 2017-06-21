package top.onee.ssm.common;

/**
 * 公共常量类
 * Created by VOREVER on 23/04/2017.
 */
public class CommonConsts {

    public static String GBK = "GBK";
    public static String UTF8 = "UTF-8";

    public static final int REDIS_TIMEOUT = 60 * 60;                /*Redis过期时间*/
    public static final int REDIS_TIMEOUT_HOUR = 60 * 60;           /*Redis一小时过期*/
    public static final int REDIS_TIMEOUT_Day = 60 * 60 * 24;       /*Redis一天过期*/

    public static final String REDIS_KEY_TEST = "common:ssm:test";  /*测试键名*/
}
