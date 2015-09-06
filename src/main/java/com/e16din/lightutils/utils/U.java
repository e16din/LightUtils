package com.e16din.lightutils.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;


public class U extends SdkUtils {

    private U() {
    }

    public static boolean isGpsEnabled(Context context) {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean isOnline(Context context) {
        if (context == null)
            return false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static boolean runIfOnline(boolean isOnline, Context context, Runnable callback) {
        boolean result = isOnline(context);
        if (result == isOnline)
            callback.run();

        return result;
    }
}
