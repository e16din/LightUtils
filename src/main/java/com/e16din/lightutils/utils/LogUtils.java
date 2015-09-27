package com.e16din.lightutils.utils;

import android.util.Log;

/**
 * Created by e16din on 02.09.15.
 */
public class LogUtils {

    public static void beep() {
        Log.d("BeepBeep", "beep beep!");
    }

    public static void beep(Class cls) {
        d(cls, "beep!");
    }

    public static void d(Class cls, String text) {
        Log.d(cls.getSimpleName(), text);
    }

    public static void e(Class cls, String text) {
        Log.e(cls.getSimpleName(), text);
    }

    public static void i(Class cls, String text) {
        Log.i(cls.getSimpleName(), text);
    }

    public static void v(Class cls, String text) {
        Log.v(cls.getSimpleName(), text);
    }

    public static void w(Class cls, String text) {
        Log.w(cls.getSimpleName(), text);
    }

    public static void wtf(Class cls, String text) {
        Log.wtf(cls.getSimpleName(), text);
    }
}
