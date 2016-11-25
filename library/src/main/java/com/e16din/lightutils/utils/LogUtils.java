package com.e16din.lightutils.utils;

import android.util.Log;

import com.e16din.lightutils.LightUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LogUtils extends TextViewUtils {

    public static final int LOG_PART_SIZE = 4000;

    public static final int DEFAULT_INDENT_SPACES = 4;


    public static void log(Object object) {
        logJsonI("U.Log", object + "");
    }

    public static void logJsonD(String message) {
        logJsonD(message, DEFAULT_INDENT_SPACES);
    }

    public static void logJsonD(String message, int indentSpaces) {
        logJsonD("logJsonD", message, indentSpaces);
    }

    public static void logJsonD(String tag, String message) {
        logJsonD(tag, message, DEFAULT_INDENT_SPACES);
    }


    public static void logJsonD(String tag, String message, int indentSpaces) {
        if (!LightUtils.isDebug()) return;

        if (message.startsWith("{")) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(message);
                final String logStr = jsonObject.toString(indentSpaces);

                LogUtils.logLongD(tag, logStr);
            } catch (JSONException e) {
                e.printStackTrace();

                LogUtils.logLongE(tag, message + "");
            }
        } else if (message.startsWith("[")) {
            JSONArray jsonArray;
            try {
                jsonArray = new JSONArray(message);

                Log.i(tag, "[");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    LogUtils.logLongD(tag,
                            object.toString(indentSpaces));
                }
                Log.i(tag, "]");


            } catch (JSONException e) {
                e.printStackTrace();

                LogUtils.logLongE(tag, message + "");
            }
        } else {
            LogUtils.logLongD(tag, message);
        }
    }

    public static void logJsonI(String message) {
        logJsonI(message, DEFAULT_INDENT_SPACES);
    }

    public static void logJsonI(String tag, String message) {
        logJsonI(tag, message, DEFAULT_INDENT_SPACES);
    }

    public static void logJsonI(String message, int indentSpaces) {
        logJsonI("logJsonI", message, indentSpaces);
    }

    public static void logJsonI(String tag, String message, int indentSpaces) {
        if (!LightUtils.isDebug()) return;

        if (message.startsWith("{")) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(message);
                final String logStr = jsonObject.toString(indentSpaces);

                LogUtils.logLongI(tag, logStr);
            } catch (JSONException e) {
                e.printStackTrace();

                LogUtils.logLongE(tag, message + "");
            }
        } else if (message.startsWith("[")) {
            JSONArray jsonArray;
            try {
                jsonArray = new JSONArray(message);

                Log.i(tag, "[");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    LogUtils.logLongI(tag,
                            object.toString(indentSpaces));
                }
                Log.i(tag, "]");


            } catch (JSONException e) {
                e.printStackTrace();

                LogUtils.logLongE(tag, message + "");
            }
        } else {
            LogUtils.logLongI(tag, message);
        }
    }

    public static void logJsonE(String message) {
        logJsonE(message, DEFAULT_INDENT_SPACES);
    }

    public static void logJsonE(String tag, String message) {
        logJsonE(tag, message, DEFAULT_INDENT_SPACES);
    }

    public static void logJsonE(String message, int indentSpaces) {
        logJsonE("logJsonE", message, indentSpaces);
    }

    public static void logJsonE(String tag, String message, int indentSpaces) {
        if (!LightUtils.isDebug()) return;

        if (message.startsWith("{")) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(message);
                final String logStr = jsonObject.toString(indentSpaces);

                LogUtils.logLongE(tag, logStr);
            } catch (JSONException e) {
                e.printStackTrace();

                LogUtils.logLongE(tag, message + "");
            }
        } else if (message.startsWith("[")) {
            JSONArray jsonArray;
            try {
                jsonArray = new JSONArray(message);

                Log.i(tag, "[");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    LogUtils.logLongE(tag,
                            object.toString(indentSpaces));
                }
                Log.i(tag, "]");


            } catch (JSONException e) {
                e.printStackTrace();

                LogUtils.logLongE(tag, message + "");
            }
        } else {
            LogUtils.logLongE(tag, message);
        }
    }


    public static void logLongI(String tag, String str) {
        if (!LightUtils.isDebug()) return;

        if (str.length() > LOG_PART_SIZE) {
            Log.i(tag, str.substring(0, LOG_PART_SIZE));
            logLongI(tag, str.substring(LOG_PART_SIZE));
        } else {
            Log.i(tag, str);
        }
    }

    public static void logLongD(String tag, String str) {
        if (!LightUtils.isDebug()) return;

        if (str.length() > LOG_PART_SIZE) {
            Log.d(tag, str.substring(0, LOG_PART_SIZE));
            logLongD(tag, str.substring(LOG_PART_SIZE));
        } else {
            Log.d(tag, str);
        }
    }

    public static void logLongE(String tag, String str) {
        if (!LightUtils.isDebug()) return;

        if (str.length() > LOG_PART_SIZE) {
            Log.e(tag, str.substring(0, LOG_PART_SIZE));
            logLongE(tag, str.substring(LOG_PART_SIZE));
        } else {
            Log.e(tag, str);
        }
    }

    public static void logLongW(String tag, String str) {
        if (!LightUtils.isDebug()) return;

        if (str.length() > LOG_PART_SIZE) {
            Log.w(tag, str.substring(0, LOG_PART_SIZE));
            logLongW(tag, str.substring(LOG_PART_SIZE));
        } else {
            Log.w(tag, str);
        }
    }

    public static void logLongV(String tag, String str) {
        if (!LightUtils.isDebug()) return;

        if (str.length() > LOG_PART_SIZE) {
            Log.v(tag, str.substring(0, LOG_PART_SIZE));
            logLongV(tag, str.substring(LOG_PART_SIZE));
        } else {
            Log.v(tag, str);
        }
    }
}
