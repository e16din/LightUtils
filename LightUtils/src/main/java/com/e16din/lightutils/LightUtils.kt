package com.e16din.lightutils

import android.app.Application

import com.e16din.topactivity.TopActivity

/**
 * Created by e16din on 14.08.15.
 */
object LightUtils {

    internal var isDebug: Boolean = false

    /**
     * Initialize LightUtils
     *
     * @param app   Application object
     * @param debug BuildConfig.DEBUG
     */
    fun init(app: Application, debug: Boolean = true) {
        TopActivity.init(app)
        isDebug = debug
    }
}

fun Application.initLightUtils() {
    LightUtils.init(this)
}
