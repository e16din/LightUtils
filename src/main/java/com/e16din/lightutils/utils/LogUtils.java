package com.e16din.lightutils.utils;

import android.util.Log;

/**
 * Created by e16din on 02.09.15.
 */
public class LogUtils {

    private static String tag = "LogUtils";

    public static void setTag(String tag) {
        LogUtils.tag = tag;
    }

    public static String getTag() {
        return tag;
    }


    public static void beep() {
        Log.d(tag, "beep beep!");
    }

    public static void beep(Class cls) {
        d(cls, "beep!");
    }

    public static void beep(String text) {
        Log.d(tag, text);
    }

    public static void log(String text) {
        Log.d(tag, text);
    }

    public static void d(Class cls, String text) {
        Log.d(cls.getSimpleName(), text);
    }

    public static void d(String text) {
        Log.d(tag, text);
    }

    public static void e(Class cls, String text) {
        Log.e(cls.getSimpleName(), text);
    }

    public static void e(String text) {
        Log.e(tag, text);
    }

    public static void i(Class cls, String text) {
        Log.i(cls.getSimpleName(), text);
    }

    public static void i(String text) {
        Log.i(tag, text);
    }

    public static void v(Class cls, String text) {
        Log.v(cls.getSimpleName(), text);
    }

    public static void v(String text) {
        Log.v(tag, text);
    }

    public static void w(Class cls, String text) {
        Log.w(cls.getSimpleName(), text);
    }

    public static void w(String text) {
        Log.w(tag, text);
    }

    public static void wtf(Class cls, String text) {
        Log.wtf(cls.getSimpleName(), text);
    }

    public static void wtf(String text) {
        Log.wtf(tag, text);
    }
}
