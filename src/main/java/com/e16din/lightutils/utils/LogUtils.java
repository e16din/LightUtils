package com.e16din.lightutils.utils;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by e16din on 02.09.15.
 */
public class LogUtils extends SecureUtils {

    private static String tag = "LogUtils";

    private static ArrayList<String> blackList = new ArrayList<>();

    public static void disableLogs(String tag) {
        blackList.add(tag);
    }

    public static void disableLogs(Class cls) {
        disableLogs(cls.getSimpleName());
    }

    public static void enableLogs(String tag) {
        blackList.remove(tag);
    }

    public static void enableLogs(Class cls) {
        enableLogs(cls.getSimpleName());
    }

    public static void setTag(String tag) {
        LogUtils.tag = tag;
    }

    public static String getTag() {
        return tag;
    }


    public static void beep() {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }
        Log.d(tag, "beep beep!");
    }

    public static void beep(Class cls) {
        for (String tag : blackList) {
            if (tag.equals(cls.getSimpleName()))
                return;
        }
        d(cls, "beep!");
    }

    public static void beep(String text) {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }
        Log.d(tag, text);
    }

    public static void log(String text) {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }
        Log.d(tag, text);
    }

    public static void d(Class cls, String text) {
        for (String tag : blackList) {
            if (tag.equals(cls.getSimpleName()))
                return;
        }
        Log.d(cls.getSimpleName(), text);
    }

    public static void d(String text) {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }

        Log.d(tag, text);
    }

    public static void e(Class cls, String text) {
        for (String tag : blackList) {
            if (tag.equals(cls.getSimpleName()))
                return;
        }
        Log.e(cls.getSimpleName(), text);
    }

    public static void e(String text) {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }

        Log.e(tag, text);
    }

    public static void i(Class cls, String text) {
        for (String tag : blackList) {
            if (tag.equals(cls.getSimpleName()))
                return;
        }

        Log.i(cls.getSimpleName(), text);
    }

    public static void i(String text) {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }

        Log.i(tag, text);
    }

    public static void v(Class cls, String text) {
        for (String tag : blackList) {
            if (tag.equals(cls.getSimpleName()))
                return;
        }

        Log.v(cls.getSimpleName(), text);
    }

    public static void v(String text) {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }

        Log.v(tag, text);
    }

    public static void w(Class cls, String text) {
        for (String tag : blackList) {
            if (tag.equals(cls.getSimpleName()))
                return;
        }

        Log.w(cls.getSimpleName(), text);
    }

    public static void w(String text) {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }

        Log.w(tag, text);
    }

    public static void wtf(Class cls, String text) {
        for (String tag : blackList) {
            if (tag.equals(cls.getSimpleName()))
                return;
        }

        Log.wtf(cls.getSimpleName(), text);
    }

    public static void wtf(String text) {
        for (String tag : blackList) {
            if (tag.equals(LogUtils.tag))
                return;
        }

        Log.wtf(tag, text);
    }
}
