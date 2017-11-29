package com.wshadow.powermonitoring.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间相关工具类
 * Created by WelkinShadow on 2017/11/24.
 */

public class TimeUtil {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间戳转为时间字符串
     *
     * @param millis 毫秒时间戳
     * @return 时间字符串
     */
    public static String millis2String(long millis) {
        return new SimpleDateFormat(DEFAULT_PATTERN, Locale.getDefault()).format(new Date(millis));
    }

    /**
     * 将时间戳转为时间字符串
     *
     * @param millis  毫秒时间戳
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String millis2String(long millis, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(millis));
    }

    /**
     * 将Date类型转为格式化时间字符串
     *
     * @param date Date类型时间
     * @return 格式化时间字符串
     */
    public static String date2String(Date date) {
        return date2String(date, DEFAULT_PATTERN);
    }

    /**
     * 将Date类型转为格式化时间字符串
     *
     * @param date    Date类型时间
     * @param pattern 时间格式
     * @return 格式化时间字符串
     */
    public static String date2String(Date date, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    /**
     * 获取当前时间字符串
     *
     * @return 时间字符串
     */
    public static String getNowTimeString() {
        return millis2String(System.currentTimeMillis(), DEFAULT_PATTERN);
    }

    /**
     * 获取当前时间字符串
     *
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String getNowTimeString(String pattern) {
        return millis2String(System.currentTimeMillis(), pattern);
    }
}
