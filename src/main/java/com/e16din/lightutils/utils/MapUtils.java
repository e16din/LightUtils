package com.e16din.lightutils.utils;

import android.content.Context;
import android.util.Log;

import com.e16din.lightutils.utils.yandex.model.Result;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


/**
 * Created by e16din on 31.08.15.
 */
public class MapUtils extends InvisibleUtils {

    public static final int DEFAULT_Z = 15;
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 450;
    public static final String TAG = "MapUtils";

    public interface OnCompleteListener {
        void onComplete(String url);
    }

    public static void searchYandexStaticMapUrlByAddress(Context context, String address,
                                                         final OnCompleteListener listener) {
        Log.d(TAG, "url: " + ("https://geocode-maps.yandex.ru/1.x/?format=json&geocode=" + address));
        Ion.with(context)
                .load("https://geocode-maps.yandex.ru/1.x/?format=json&geocode=" + address)
                .as(Result.class)
                .setCallback(new FutureCallback<Result>() {
                    @Override
                    public void onCompleted(Exception e, Result result) {
                        final String[] pos = result.getResponse().getGeoObjectCollection()
                                .getFeatureMember().get(0).getGeoObject().getPoint().getPos();

                        listener.onComplete(getYandexStaticMapUrl(pos[0], pos[1]));
                    }
                });
    }

    public static String getYandexStaticMapUrl(float lat, float lon) {
        return getYandexStaticMapUrl(lat, lon, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static String getYandexStaticMapUrl(float lat, float lon, int width, int height) {
        return getYandexStaticMapUrl(lat, lon, width, height, DEFAULT_Z);
    }

    public static String getYandexStaticMapUrl(float lat, float lon, int width, int height, int z) {
        final String result = "https://static-maps.yandex.ru/1.x/?l=map&lang=ru-RU&ll=" + lat + "%2C" + lon +
                "&origin=jsapi-constructor&pt=" + Float.toString(lat) + "%2C" + Float.toString(lon) +
                "%2Cpm2lbl&size=" + Integer.toString(width) + "%2C" + Integer.toString(height) +
                "&z=" + z;
        Log.d(TAG, "url: " + result);
        return result;
    }

    public static String getYandexStaticMapUrl(String lat, String lon) {
        return getYandexStaticMapUrl(lat, lon, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static String getYandexStaticMapUrl(String lat, String lon, int width, int height) {
        return getYandexStaticMapUrl(lat, lon, width, height, DEFAULT_Z);
    }

    public static String getYandexStaticMapUrl(String lat, String lon, int width, int height, int z) {
        final String result = "https://static-maps.yandex.ru/1.x/?l=map&lang=ru-RU&ll=" + lat + "%2C" + lon +
                "&origin=jsapi-constructor&pt=" + lat + "%2C" + lon +
                "%2Cpm2lbl&size=" + Integer.toString(width) + "%2C" + Integer.toString(height) +
                "&z=" + z;
        Log.d(TAG, "url: " + result);
        return result;
    }
}
