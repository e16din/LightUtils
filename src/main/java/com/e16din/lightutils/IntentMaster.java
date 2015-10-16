package com.e16din.lightutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by e16din on 12.08.15.
 */
public class IntentMaster {

    private IntentMaster() {
    }

    public static String KEY_DATA = "data";

    public static Serializable getExtra(Intent intent, String key) {
        return intent.getExtras().getSerializable(key);
    }

    public static Serializable getExtra(Intent intent) {
        return getExtra(intent, 0);
    }

    public static Serializable getExtra(Intent intent, int position) {
        return getExtra(intent, KEY_DATA + "_" + position);
    }

    public static Serializable getExtra(Activity activity) {
        return getExtra(activity, 0);
    }

    public static Serializable getExtra(Activity activity, String key) {
        return getExtra(activity.getIntent(), key);
    }

    public static Serializable getExtra(Activity activity, int position) {
        return getExtra(activity.getIntent(), KEY_DATA + "_" + position);
    }


    public static Parcelable getExtraP(Intent intent, String key) {
        return intent.getExtras().getParcelable(key);
    }

    public static Parcelable getExtraP(Intent intent) {
        return getExtraP(intent, 0);
    }

    public static Parcelable getExtraP(Intent intent, int position) {
        return getExtraP(intent, KEY_DATA + "_" + position);
    }

    public static Parcelable getExtraP(Activity activity) {
        return getExtraP(activity, 0);
    }

    public static Parcelable getExtraP(Activity activity, String key) {
        return getExtraP(activity.getIntent(), key);
    }

    public static Parcelable getExtraP(Activity activity, int position) {
        return getExtraP(activity.getIntent(), KEY_DATA + "_" + position);
    }


    public static boolean containsKey(Intent intent, String key) {
        return intent.getExtras() != null && intent.getExtras().containsKey(key);
    }

    public static int getExtrasCount(Intent intent) {
        return intent.getExtras() == null ? 0 : intent.getExtras().size();
    }

    public static int getExtrasCount(Activity activity) {
        return getExtrasCount(activity.getIntent());
    }

    public static boolean hasExtra(Intent intent) {
        return getExtrasCount(intent) > 0;
    }

    public static boolean hasExtra(Activity activity) {
        return hasExtra(activity.getIntent());
    }

    public static Intent createIntent(Context context, Class cls, Serializable... data) {
        Intent intent = new Intent(context, cls);
        putExtra(intent, data);
        return intent;
    }

    public static Intent createIntent(Context context, Class cls, Parcelable... data) {
        Intent intent = new Intent(context, cls);
        putExtra(intent, data);
        return intent;
    }

    public static Intent createResultIntent(Activity activity, Serializable... data) {
        Intent intent = new Intent();
        activity.setResult(Activity.RESULT_OK, intent);
        putExtra(intent, data);
        return intent;
    }

    public static Intent createResultIntent(Activity activity, Parcelable... data) {
        Intent intent = new Intent();
        activity.setResult(Activity.RESULT_OK, intent);
        putExtra(intent, data);
        return intent;
    }

    public static Intent createResultIntent(Activity activity, Data... data) {
        Intent intent = new Intent();
        activity.setResult(Activity.RESULT_OK, intent);
        putExtra(intent, data);
        return intent;
    }

    public static void finishWithResult(Activity activity, Serializable... data) {
        createResultIntent(activity, data);
        activity.finish();
    }

    public static void finishWithResult(Activity activity, Parcelable... data) {
        createResultIntent(activity, data);
        activity.finish();
    }

    public static void finishWithResult(Activity activity, Data... data) {
        createResultIntent(activity, data);
        activity.finish();
    }

    public static Intent createIntent(Context context, Class cls, Data... data) {
        Intent intent = new Intent(context, cls);
        putExtra(intent, data);
        return intent;
    }

    public static Intent createIntent(Context context, Class cls) {
        return new Intent(context, cls);
    }

    public static void startActivity(Context context, Class cls) {
        Intent intent = createIntent(context, cls);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class cls, Data... data) {
        Intent intent = createIntent(context, cls, data);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class cls, Serializable... data) {
        Intent intent = createIntent(context, cls, data);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class cls, Parcelable... data) {
        Intent intent = createIntent(context, cls, data);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, Class cls, int requestCode,
                                              Data... data) {
        Intent intent = createIntent(activity, cls, data);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class cls, int requestCode,
                                              Serializable... data) {
        Intent intent = createIntent(activity, cls, data);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class cls, int requestCode,
                                              Parcelable... data) {
        Intent intent = createIntent(activity, cls, data);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult0(Activity activity, Class cls, Data... data) {
        startActivityForResult(activity, cls, 0, data);
    }

    public static void startActivityForResult0(Activity activity, Class cls,
                                               Serializable... data) {
        startActivityForResult(activity, cls, 0, data);
    }

    public static void startActivityForResult0(Activity activity, Class cls,
                                               Parcelable... data) {
        startActivityForResult(activity, cls, 0, data);
    }

    public static void putExtra(Intent intent, Data[] data) {
        for (int i = 0; i < data.length; i++) {
            intent.putExtra(data[i].getKey(), data[i].getValue());
        }
    }

    public static void putExtra(Intent intent, Serializable[] data) {
        for (int i = 0; i < data.length; i++) {
            intent.putExtra(KEY_DATA + "_" + i, data[i]);
        }
    }

    public static void putExtra(Intent intent, Parcelable[] data) {
        for (int i = 0; i < data.length; i++) {
            intent.putExtra(KEY_DATA + "_" + i, data[i]);
        }
    }

    ///Services

    public static void startService(Context context, Class cls) {
        Intent intent = createIntent(context, cls);
        context.startService(intent);
    }

    //use this permission
    //<uses-permission android:name="android.permission.CALL_PHONE" />
    public static void call(Activity activity, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        activity.startActivity(intent);
    }

    public static void sendSms(Activity activity, String phone, String message) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + phone));
        intent.putExtra("sms_body", message);
        activity.startActivity(intent);
    }

    public static void openMap(Activity activity, double lat, double lng, String title) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + lat + "," + lng + " (" + title + ")"));
        activity.startActivity(intent);
    }

    public static void openGoogleMap(Activity activity, String text) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + text));
        intent.setPackage("com.google.android.apps.maps");
        activity.startActivity(intent);
    }

    public static void openNavigationMap(Activity activity, String address) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + address));
        activity.startActivity(intent);
    }

    public static void openMap(Activity activity, String lat, String lng, String title) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + lat + "," + lng + " (" + title + ")"));
        activity.startActivity(intent);
    }

    public static void openMap(Activity activity, double lat, double lng) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + lat + "," + lng));
        activity.startActivity(intent);
    }

    public static void openMap(Activity activity, String lat, String lng) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + lat + "," + lng));
        activity.startActivity(intent);
    }
}
