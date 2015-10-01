package com.e16din.lightutils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Set;

public final class DataManager {

    private Context context;
    private Gson gson = new Gson();

    private static class Holder {
        public static final DataManager HOLDER_INSTANCE = new DataManager();
    }

    private DataManager() {
    }

    public static void init(final Context context) {
        if (Holder.HOLDER_INSTANCE.context == null) {
            Holder.HOLDER_INSTANCE.context = context.getApplicationContext();
        }
    }

    public static DataManager getInstance() {
        return Holder.HOLDER_INSTANCE;
    }

    public SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public <T> void save(final String key, final T data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putString(key, gson.toJson(data));
        editor.apply();
    }

    public void save(final String key, final boolean data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putBoolean(key, data);
        editor.apply();
    }

    public void save(final String key, final String data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putString(key, data);
        editor.apply();
    }

    public void save(final String key, final int data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putInt(key, data);
        editor.apply();
    }

    public void save(final String key, final long data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putLong(key, data);
        editor.apply();
    }

    public void save(final String key, final float data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putFloat(key, data);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void save(final String key, final Set<String> data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putStringSet(key, data);
        editor.apply();
    }

    public <T> T load(final String key, final Type type) {
        final String string = getDefaultSharedPreferences().getString(key, null);
        if (string == null) {
            return null;
        }
        return gson.fromJson(string, type);
    }

    public String loadString(final String key) {
        return getDefaultSharedPreferences().getString(key, null);
    }

    public boolean loadBool(final String key) {
        return getDefaultSharedPreferences().getBoolean(key, false);
    }

    public int loadInt(final String key) {
        return getDefaultSharedPreferences().getInt(key, -1);
    }

    public long loadLong(final String key) {
        return getDefaultSharedPreferences().getLong(key, -1);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> loadStringSet(final String key) {
        return getDefaultSharedPreferences().getStringSet(key, null);
    }

    public float loadFloat(final String key) {
        return getDefaultSharedPreferences().getFloat(key, -1f);
    }

    public void clear() {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }
}
