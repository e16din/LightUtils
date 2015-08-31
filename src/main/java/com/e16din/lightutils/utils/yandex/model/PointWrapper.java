package com.e16din.lightutils.utils.yandex.model;

import java.io.Serializable;

/**
 * Created by e16din on 31.08.15.
 */
public class PointWrapper implements Serializable {
    private String pos = null;

    public String[] getPos() {
        return pos.split(" ");
    }
}
