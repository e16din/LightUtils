package com.e16din.lightutils.utils.yandex.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by e16din on 31.08.15.
 */
public class GeoObject implements Serializable {

    //        "metaDataProperty":
//        {
//            "GeocoderMetaData":
//            {
//                "kind": "locality",
//                    "text": "Россия, Москва",
//                    "precision": "other",
//                    "AddressDetails":
//                {
//                    "Country":
//                    {
//                        "AddressLine": "Москва",
//                            "CountryNameCode": "RU",
//                            "CountryName": "Россия",
//                            "AdministrativeArea":
//                        {
//                            "AdministrativeAreaName": "Центральный федеральный округ",
//                                "SubAdministrativeArea":
//                            {
//                                "SubAdministrativeAreaName": "Москва",
//                                    "Locality":
//                                {
//                                    "LocalityName": "Москва"
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        },
//        "description": "Россия",
//            "name": "Москва",
//            "boundedBy":
//        {
//            "Envelope":
//            {
//                "lowerCorner": "37.32624 55.491126",
//                    "upperCorner": "37.967682 55.957565"
//            }
//        },
    @SerializedName("Point")
    private PointWrapper point = null;

    public PointWrapper getPoint() {
        return point;
    }

    public void setPoint(PointWrapper point) {
        this.point = point;
    }
}
