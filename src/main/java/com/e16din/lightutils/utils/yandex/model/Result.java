package com.e16din.lightutils.utils.yandex.model;

import java.io.Serializable;

/**
 * Created by e16din on 31.08.15.
 */
@Deprecated
public class Result implements Serializable {

    private Response response = null;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
