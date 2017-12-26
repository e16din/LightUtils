package com.e16din.lightutils.app

import android.app.Application
import com.e16din.lightutils.initLightUtils


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initLightUtils()
    }
}