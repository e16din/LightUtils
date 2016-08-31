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

import com.e16din.lightutils.LightUtils;
import com.e16din.lightutils.tools.AgileRunnable;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public final class U extends ResourcesUtils {

    public static final int WRONG_VALUE = -100500;

    private static List<AgileRunnable> mTickers;

    private U() {
    }

    private static Handler handler = new Handler();

    public static Handler getHandler() {
        return handler;
    }

    public static void log(Object object) {
        LogUtils.log(object);
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

    public static void startTicker(final Long interval, final int count, final AgileRunnable onTick) {
        if (mTickers == null) {
            mTickers = new ArrayList<>();
        }
        mTickers.add(onTick);


        U.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onTick.run();

                if (!onTick.isCanceled() && count - 1 > 0) {
                    startTicker(interval, count - 1, onTick);
                }
            }
        }, interval);
    }

    public static void breakAllTickers() {
        if (mTickers == null) return;

        for (AgileRunnable r : mTickers) {
            r.cancel();
        }

        mTickers.clear();
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
