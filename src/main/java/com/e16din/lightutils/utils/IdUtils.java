package com.e16din.lightutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by e16din on 14.08.15.
 */
public class IdUtils extends DateTimeUtils {

    public static String getImei(@NonNull Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getUdid(@NonNull Context context) {
        // for android sdk >= 9
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getPackageVersionName(@NonNull Context context) throws PackageManager.NameNotFoundException {
        return getPackageInfo(context).versionName;
    }

    public static int getVersionCode(@NonNull Context context) throws PackageManager.NameNotFoundException {
        return getPackageInfo(context).versionCode;
    }

    public static PackageInfo getPackageInfo(@NonNull Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    }

    public static String getDevGuid(@NonNull Activity activity) {
        final TelephonyManager tm = (TelephonyManager) activity.getBaseContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice = "" + tm.getDeviceId();
        final String tmSerial = "" + tm.getSimSerialNumber();
        final String androidId = "" + android.provider.Settings.Secure.getString(activity.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());

        return deviceUuid.toString();
    }

    public static String getAppGuid(@NonNull Activity activity) {
        final String androidId = "" + android.provider.Settings.Secure.getString(activity.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), System.currentTimeMillis());

        return deviceUuid.toString();
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
