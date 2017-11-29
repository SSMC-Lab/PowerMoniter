package com.wshadow.powermonitoring.util;

import android.util.Log;

/**
 * 日志相关工具类
 * Created by WelkinShadow on 2017/11/19.
 */

public class LogUtil {

    private static boolean LOGV = true;
    private static boolean LOGI = true;
    private static boolean LOGD = true;
    private static boolean LOGW = true;
    private static boolean LOGE = true;

    private static String getTag() {
        StackTraceElement[] trace = new Throwable().getStackTrace();
        String callingClass = "";
        for (int i = 2, len = trace.length; i < len; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    /**
     * Verbose日志
     *
     * @param msg 消息
     */
    public static void v(String msg) {
        v(getTag(), msg);
    }

    /**
     * Verbose日志
     *
     * @param tag 标签
     * @param msg 消息
     */
    public static void v(String tag, String msg) {
        if (LOGV)
            Log.v(tag,msg);
    }

    /**
     * Info日志
     *
     * @param msg 消息
     */
    public static void i(String msg) {
        i(getTag(), msg);
    }

    /**
     * Info日志
     *
     * @param tag 标签
     * @param msg 消息
     */
    public static void i(String tag, String msg) {
        if (LOGI)
            Log.i(tag,msg);
    }

    /**
     * Debug日志
     *
     * @param msg 消息
     */
    public static void d(String msg) {
        d(getTag(), msg);
    }

    /**
     * Debug日志
     *
     * @param tag 标签
     * @param msg 消息
     */
    public static void d(String tag, String msg) {
        if (LOGD)
            Log.d(tag,msg);
    }

    /**
     * Warning日志
     *
     * @param msg 消息
     */
    public static void w(String msg) {
        w(getTag(), msg);
    }

    /**
     * Warning日志
     *
     * @param tag 标签
     * @param msg 消息
     */
    public static void w(String tag, String msg) {
        if (LOGW)
            Log.w(tag,msg);
    }

    /**
     * Error日志
     *
     * @param msg 消息
     */
    public static void e(String msg) {
        e(getTag(), msg);
    }

    /**
     * Error日志
     *
     * @param tag 标签
     * @param msg 消息
     */
    public static void e(String tag, String msg) {
        if (LOGE)
            Log.e(tag,msg);
    }
}
