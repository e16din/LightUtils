package com.e16din.lightutils.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.e16din.lightutils.LightUtils;

import java.util.List;


public final class U extends TextViewUtils {

    public static final int WRONG_VALUE = -100500;


    private U() {
    }

    private static Handler handler = new Handler();

    public static Handler getHandler() {
        return handler;
    }

    public static void log(String text) {
        Log.i("U.Log", text);
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public static boolean isGpsEnabled() {
        final Context context = LightUtils.getInstance().getContext();

        final LocationManager manager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean isOnline() {
        final Context context = LightUtils.getInstance().getContext();

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static boolean runIfOnline(boolean isOnline, Runnable callback) {
        boolean result = isOnline();
        if (result == isOnline)
            callback.run();

        return result;
    }

    public static boolean isCallable(Context context, Intent intent) {
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    public static boolean contains(int from, int to, int value) {
        return value >= from && value <= to;
    }

    public static boolean contains(float from, float to, float value) {
        return value >= from && value <= to;
    }

    public static boolean allAbsValuesSmallerThan(List<Float> values, float thanX) {
        boolean isClick = true;

        for (float value : values) {
            if (Math.abs(value) > thanX) {
                isClick = false;
                break;
            }
        }

        return isClick;
    }

    public static boolean tryThis(Runnable runnable) {
        try {
            if (runnable != null) {
                runnable.run();
                return true;
            }
        } catch (NullPointerException | WindowManager.BadTokenException | IllegalStateException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Function from Objects(api level 19) for compatibility with old versions
     * <p/>
     * Null-safe equivalent of {@code a.equals(b)}.
     */
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }
}
