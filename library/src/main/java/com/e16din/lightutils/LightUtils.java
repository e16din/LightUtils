package com.e16din.lightutils;

import android.content.Context;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by e16din on 14.08.15.
 */
public final class LightUtils {

    private static volatile Context sContext;
    private static boolean sDebug;

    private LightUtils() {
    }

    /**
     * Initialize LightUtils
     *
     * @param context Application context
     * @param debug   BuildConfig.DEBUG
     */
    public static void init(final Context context, boolean debug) {
        sContext = context.getApplicationContext();
        sDebug = debug;
    }

    public static Resources getResources() {
        return sContext.getResources();
    }

    @Deprecated
    public static Locale getCurrentLocale() {
        return getResources().getConfiguration().locale;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(Context context) {
        sContext = context;
    }
}
