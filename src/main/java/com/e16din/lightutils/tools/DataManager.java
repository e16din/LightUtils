package com.e16din.lightutils.tools;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.e16din.lightutils.LightUtils;

import java.lang.reflect.Type;
import java.util.Set;

public final class DataManager {

    private boolean needCommit = true;

    private static class Holder {
        public static final DataManager HOLDER_INSTANCE = new DataManager();
    }

    private DataManager() {
    }

    public static DataManager getInstance() {
        return Holder.HOLDER_INSTANCE;
    }

    public SharedPreferences getDefaultSharedPreferences() {
        try {
            return PreferenceManager.getDefaultSharedPreferences(LightUtils.getInstance().getContext());
        } catch (NullPointerException e) {
            throw new NullPointerException("Please initialize LightUtils before use DataManager. LightUtils.init(context))");
        }
    }

    public boolean contains(@NonNull final String key) {
        return getDefaultSharedPreferences().contains(key);
    }

    public <T> void save(@NonNull final String key, final T data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putString(key, LightUtils.getInstance().getGson().toJson(data));
        apply(editor);
    }

    public void save(@NonNull final String key, final boolean data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putBoolean(key, data);
        apply(editor);
    }

    public void save(@NonNull final String key, final String data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putString(key, data);
        apply(editor);
    }

    public void save(@NonNull final String key, final int data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putInt(key, (int) data);
        apply(editor);
    }

    public void save(@NonNull final String key, final long data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putLong(key, (long) data);
        apply(editor);
    }

    public void save(@NonNull final String key, final float data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putFloat(key, (float) data);
        apply(editor);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void save(@NonNull final String key, final Set<String> data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putStringSet(key, data);
        apply(editor);
    }

    private void apply(SharedPreferences.Editor editor) {
        if (needCommit) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public void remove(@NonNull String key) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.remove(key);
        apply(editor);
    }

    public void clear() {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.clear();
        apply(editor);
    }

    public <T> T load(@NonNull final String key, final Type type) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        final String string = getDefaultSharedPreferences().getString(key, null);
        if (string == null) {
            return null;
        }

        return LightUtils.getInstance().getGson().fromJson(string, type);
    }

    public String loadString(@NonNull final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        return getDefaultSharedPreferences().getString(key, null);
    }

    public boolean loadBool(@NonNull final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return false;
        }

        return getDefaultSharedPreferences().getBoolean(key, false);
    }

    public int loadInt(@NonNull final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return -1;
        }

        return getDefaultSharedPreferences().getInt(key, -1);
    }

    public long loadLong(@NonNull final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return -1L;
        }

        return getDefaultSharedPreferences().getLong(key, -1L);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> loadStringSet(@NonNull final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        return getDefaultSharedPreferences().getStringSet(key, null);
    }

    public float loadFloat(@NonNull final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return -1f;
        }

        return getDefaultSharedPreferences().getFloat(key, -1f);
    }


    public boolean needCommit() {
        return needCommit;
    }

    public void setNeedCommit(boolean needCommit) {
        this.needCommit = needCommit;
    }
}
