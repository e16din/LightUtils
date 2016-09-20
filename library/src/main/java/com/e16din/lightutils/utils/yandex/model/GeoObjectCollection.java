package com.e16din.lightutils.utils.yandex.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by e16din on 31.08.15.
 */
@Deprecated
public class GeoObjectCollection implements Serializable {
//    "metaDataProperty":
//    {
//        "GeocoderResponseMetaData":
//        {
//            "request": "Москва",
//                "found": "49",
//                "results": "10"
//        }
//    },
    private ArrayList<GeoObjectWrapper> featureMember = null;

    public ArrayList<GeoObjectWrapper> getFeatureMember() {
        return featureMember;
    }

    public void setFeatureMember(ArrayList<GeoObjectWrapper> featureMember) {
        this.featureMember = featureMember;
    }
}
