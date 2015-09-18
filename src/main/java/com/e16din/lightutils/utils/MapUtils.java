package com.e16din.lightutils.utils;

import android.content.Context;
import android.util.Log;

import com.e16din.lightutils.utils.yandex.model.Result;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


/**
 * Created by e16din on 31.08.15.
 */
public class MapUtils extends ViewUtils {
    public static final int DEFAULT_Z = 15;
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 450;
    public static final String TAG = "MapUtils";
    public static final String URL_MAPS_YANDEX_GEOCODE = "https://geocode-maps.yandex.ru/1.x/?format=json&geocode=";

    public interface OnYandexMapUrlFoundListener {
        void onComplete(String url);
    }

    public interface OnLocationFoundListener {
        void onComplete(String latitude, String longitude);
    }

    public static void getLocationByAddress(Context context, String address, final OnLocationFoundListener listener) {
        Log.d(TAG, "url: " + ("https://geocode-maps.yandex.ru/1.x/?format=json&geocode=" + address));
        Ion.with(context)
                .load(URL_MAPS_YANDEX_GEOCODE + address)
                .as(Result.class)
                .setCallback(new FutureCallback<Result>() {
                    @Override
                    public void onCompleted(Exception e, Result result) {
                        final String[] pos = result.getResponse().getGeoObjectCollection()
                                .getFeatureMember().get(0).getGeoObject().getPoint().getPos();
                        listener.onComplete(pos[0], pos[1]);
                    }
                });
    }

    public static void searchYandexStaticMapUrlByAddress(Context context, String address,
                                                         final OnYandexMapUrlFoundListener listener) {
        getLocationByAddress(context, address, new OnLocationFoundListener() {
            @Override
            public void onComplete(String latitude, String longitude) {
                listener.onComplete(getYandexStaticMapUrl(latitude, longitude));
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

    public static float calculateDistance(double lat1, double lng1, double lat2, double lng2, boolean miles) {
        lat1 *= Math.PI / 180;
        lng1 *= Math.PI / 180;
        lat2 *= Math.PI / 180;
        lng2 *= Math.PI / 180;

        final float r = 6372.797f; // radius of Earth in km

        double dlat = lat2 - lat1;
        double dlng = lng2 - lng1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlng / 2) * Math.sin(dlng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double km = r * c;

        return (float) (miles ? (km * 0.621371192) : km);
    }
}
