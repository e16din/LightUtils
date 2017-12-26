package com.e16din.topactivity

import android.app.Activity
import android.app.Application
import android.os.Bundle

class LifecycleHandler : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {}

    override fun onActivityPaused(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityStarted(activity: Activity?) {
        setActivity(activity)
    }

    override fun onActivityStopped(activity: Activity?) {
        val topActivity = TopActivity.get()

        if (topActivity != null && topActivity == activity) {
            TopActivity.set(null)
        }
    }

    private fun setActivity(activity: Activity?) {
        TopActivity.set(activity)
    }
}