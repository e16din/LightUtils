package com.e16din.lightutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

/**
 * Created by e16din on 12.08.15.
 */
public class IntentMaster {

    public static final String KEY_DATA = "data";

    public static final Serializable getExtra(Intent intent, String key) {
        return intent.getExtras().getSerializable(key);
    }

    public static final Serializable getExtra(Intent intent) {
        return getExtra(intent, 0);
    }

    public static final Serializable getExtra(Intent intent, int position) {
        return getExtra(intent, KEY_DATA + "_" + position);
    }

    public static final Serializable getExtra(Activity activity) {
        return getExtra(activity, 0);
    }

    public static final Serializable getExtra(Activity activity, String key) {
        return getExtra(activity.getIntent(), key);
    }

    public static final Serializable getExtra(Activity activity, int position) {
        return getExtra(activity.getIntent(), KEY_DATA + "_" + position);
    }

    public static final boolean containsKey(Intent intent, String key) {
        return intent.getExtras() != null && intent.getExtras().containsKey(key);
    }

    public static final Intent createActivityIntent(Context context, Class cls,
                                                    Serializable... data) {
        Intent intent = new Intent(context, cls);
        putExtra(intent, data);
        return intent;
    }

    public static final Intent createResultIntent(Activity activity, Serializable... data) {
        Intent intent = new Intent();
        activity.setResult(Activity.RESULT_OK, intent);
        putExtra(intent, data);
        return intent;
    }

    public static final Intent createResultIntent(Activity activity, Data... data) {
        Intent intent = new Intent();
        activity.setResult(Activity.RESULT_OK, intent);
        putExtra(intent, data);
        return intent;
    }

    public static final void finishWithResult(Activity activity, Serializable... data) {
        createResultIntent(activity, data);
        activity.finish();
    }

    public static final void finishWithResult(Activity activity, Data... data) {
        createResultIntent(activity, data);
        activity.finish();
    }

    public static final Intent createActivityIntent(Context context, Class cls, Data... data) {
        Intent intent = new Intent(context, cls);
        putExtra(intent, data);
        return intent;
    }

    public static final void startActivity(Context context, Class cls, Data... data) {
        Intent intent = createActivityIntent(context, cls, data);
        context.startActivity(intent);
    }

    public static final void startActivity(Context context, Class cls, Serializable... data) {
        Intent intent = createActivityIntent(context, cls, data);
        context.startActivity(intent);
    }

    public static final void startActivityForResult(Activity activity, Class cls, int requestCode,
                                                    Data... data) {
        Intent intent = createActivityIntent(activity, cls, data);
        activity.startActivityForResult(intent, requestCode);
    }

    public static final void startActivityForResult(Activity activity, Class cls, int requestCode,
                                                    Serializable... data) {
        Intent intent = createActivityIntent(activity, cls, data);
        activity.startActivityForResult(intent, requestCode);
    }

    public static final void startActivityForResult(Activity activity, Class cls, Data... data) {
        startActivityForResult(activity, cls, 0, data);
    }

    public static final void startActivityForResult(Activity activity, Class cls,
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
}
