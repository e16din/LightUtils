package com.e16din.lightutils;

import android.content.Context;
import android.content.res.Resources;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.Locale;

/**
 * Created by e16din on 14.08.15.
 */
public final class LightUtils {

    private Context mContext;
    private boolean mDebug;

    private LightUtils() {
    }

    private static class Holder {
        public static final LightUtils HOLDER_INSTANCE = new LightUtils();
    }

    public static LightUtils getInstance() {
        return Holder.HOLDER_INSTANCE;
    }

    /**
     * Initialize LightUtils and JodaTimeAndroid
     *
     * @param context Application context
     * @param debug   BuildConfig.DEBUG
     */
    public static void init(final Context context, boolean debug) {
        if (Holder.HOLDER_INSTANCE.mContext == null) {
            Holder.HOLDER_INSTANCE.mContext = context.getApplicationContext();
            JodaTimeAndroid.init(Holder.HOLDER_INSTANCE.mContext);
            Holder.HOLDER_INSTANCE.mDebug = debug;
        }
    }

    /**
     * @deprecated Use init(final Context mContext, boolean debug)
     */
    @Deprecated
    public static void init(final Context context) {
        if (Holder.HOLDER_INSTANCE.mContext == null) {
            Holder.HOLDER_INSTANCE.mContext = context.getApplicationContext();
            JodaTimeAndroid.init(Holder.HOLDER_INSTANCE.mContext);
        }
    }

    public Resources getResources() {
        return mContext.getResources();
    }

    @Deprecated
    public Locale getCurrentLocale() {
        return mContext.getResources().getConfiguration().locale;
    }

    public boolean isDebug() {
        return mDebug;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
}
