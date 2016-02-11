package com.e16din.lightutils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Locale;

/**
 * Created by e16din on 14.08.15.
 */
public final class LightUtils {

    private Context context;
    private Gson gson = new Gson();

    private LightUtils() {
    }

    private static class Holder {
        public static final LightUtils HOLDER_INSTANCE = new LightUtils();
    }

    public static LightUtils getInstance() {
        return Holder.HOLDER_INSTANCE;
    }

    public static void init(@NonNull final Context context) {
        if (Holder.HOLDER_INSTANCE.context == null) {
            Holder.HOLDER_INSTANCE.context = context.getApplicationContext();
        }
    }

    public Resources getResources() {
        return context.getResources();
    }

    public Locale getCurrentLocale() {
        return context.getResources().getConfiguration().locale;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
