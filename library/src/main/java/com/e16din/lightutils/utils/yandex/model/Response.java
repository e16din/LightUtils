package com.e16din.lightutils.utils.yandex.model;

import java.io.Serializable;

/**
 * Created by e16din on 31.08.15.
 */
@Deprecated
public class Response implements Serializable {

    //todo: @SerializedName("GeoObjectCollection")
    private GeoObjectCollection geoObjectCollection = null;

    public GeoObjectCollection getGeoObjectCollection() {
        return geoObjectCollection;
    }

    public void setGeoObjectCollection(GeoObjectCollection geoObjectCollection) {
        this.geoObjectCollection = geoObjectCollection;
    }
}
