package com.e16din.lightutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.UUID;

import static com.e16din.topactivity.TopActivityKt.app;

/**
 * Created by e16din on 14.08.15.
 */
public class IdUtils extends SocialUtils {

    public static String getImei() {
        TelephonyManager tm = (TelephonyManager) app().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getUdid() {
        // for android sdk >= 9
        return Settings.Secure.getString(app().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getPackageVersionName() throws PackageManager.NameNotFoundException {
        return getPackageInfo().versionName;
    }

    public static int getVersionCode() throws PackageManager.NameNotFoundException {
        return getPackageInfo().versionCode;
    }

    public static PackageInfo getPackageInfo() throws PackageManager.NameNotFoundException {
        final Context context = app();
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    }

    public static String getDevGuid(Activity activity) {
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

    public static String getAppGuid(Activity activity) {
        final String androidId = "" + android.provider.Settings.Secure.getString(activity.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), System.currentTimeMillis());

        return deviceUuid.toString();
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
