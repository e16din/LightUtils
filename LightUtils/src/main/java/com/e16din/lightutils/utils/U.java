package com.e16din.lightutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.util.SparseArray;
import android.view.WindowManager;
import android.webkit.CookieManager;

import java.util.List;

import static com.e16din.topactivity.TopActivityKt.app;


@SuppressWarnings("unused")
public final class U extends ResourcesUtils {

    public static final int WRONG_VALUE = -100500;

    private U() {
    }

    private static Handler sHandler = new Handler();

    public static Handler getHandler() {
        return sHandler;
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
        final LocationManager manager =
                (LocationManager) app().getSystemService(Context.LOCATION_SERVICE);

        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) app().getSystemService(Context.CONNECTIVITY_SERVICE);

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
        return tryThis(runnable, true);
    }

    public static boolean tryThis(Runnable runnable, boolean needIgnore) {
        if (!needIgnore) {
            runnable.run();
            return true;
        }

        try {
            runnable.run();
            return true;
        } catch (NullPointerException | WindowManager.BadTokenException | IllegalStateException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void clearCookie() {
        CookieManager cookieManager = CookieManager.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies(null);
        } else {
            cookieManager.removeAllCookie();
        }
    }

    public static <T> void forEach(SparseArray<T> array, SparseForEachCallback<T> callback) {
        for (int i = 0; i < array.size(); i++) {
            callback.onValue(array.valueAt(i), i, array.keyAt(i));
        }
    }

    /**
     * Function from Objects(api level 19) for compatibility with old versions
     * <p/>
     * Null-safe equivalent of {@code a.equals(b)}.
     */
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    /**
     * Get activity from context object
     *
     * @param context the context
     * @return object of Activity or null if it is not Activity
     */
    public static Activity scanForActivity(Context context) {
        if (context == null)
            return null;
        else if (context instanceof Activity)
            return (Activity) context;
        else if (context instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) context).getBaseContext());

        return null;
    }

    public interface SparseForEachCallback<T> {
        void onValue(T value, int position, int key);
    }
}