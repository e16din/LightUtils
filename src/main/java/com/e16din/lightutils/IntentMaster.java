package com.e16din.lightutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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

    public static boolean containsKey(Intent intent, String key) {
        return intent.getExtras() != null && intent.getExtras().containsKey(key);
    }

    public static Intent createActivityIntent(Context context, Class cls,
                                              Serializable... data) {
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

    public static void finishWithResult(Activity activity, Data... data) {
        createResultIntent(activity, data);
        activity.finish();
    }

    public static Intent createActivityIntent(Context context, Class cls, Data... data) {
        Intent intent = new Intent(context, cls);
        putExtra(intent, data);
        return intent;
    }

    public static void startActivity(Context context, Class cls, Data... data) {
        Intent intent = createActivityIntent(context, cls, data);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class cls, Serializable... data) {
        Intent intent = createActivityIntent(context, cls, data);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, Class cls, int requestCode,
                                              Data... data) {
        Intent intent = createActivityIntent(activity, cls, data);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class cls, int requestCode,
                                              Serializable... data) {
        Intent intent = createActivityIntent(activity, cls, data);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult0(Activity activity, Class cls, Data... data) {
        startActivityForResult(activity, cls, 0, data);
    }

    public static void startActivityForResult0(Activity activity, Class cls,
                                               Serializable... data) {
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
}
