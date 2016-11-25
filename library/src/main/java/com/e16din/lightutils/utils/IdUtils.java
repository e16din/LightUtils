package com.e16din.lightutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.e16din.lightutils.LightUtils;

import java.util.UUID;

/**
 * Created by e16din on 14.08.15.
 */
public class IdUtils extends SocialUtils {

    public static String getImei() {
        final Context context = LightUtils.getContext();

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getUdid() {
        final Context context = LightUtils.getContext();
        // for android sdk >= 9
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getPackageVersionName() throws PackageManager.NameNotFoundException {
        return getPackageInfo().versionName;
    }

    public static int getVersionCode() throws PackageManager.NameNotFoundException {
        return getPackageInfo().versionCode;
    }

    public static PackageInfo getPackageInfo() throws PackageManager.NameNotFoundException {
        final Context context = LightUtils.getContext();
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
