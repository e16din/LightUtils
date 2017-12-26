package com.e16din.topactivity

import android.app.Activity
import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

typealias OnNextTopActivityListener = ((Activity?) -> Unit)

object TopActivity {

    internal var applicationRef: WeakReference<out Application>? = null
    internal var onNextTopActivityListener: WeakReference<OnNextTopActivityListener>? = null
    private var activityRef: WeakReference<out Activity>? = null

    @JvmStatic
    fun set(activity: Activity?) {
        activityRef = if (activity == null) null else WeakReference(activity)

        onNextTopActivityListener?.get()?.invoke(activity)
    }

    @JvmStatic
    fun get(): Activity? = if (activityRef == null) null else activityRef!!.get()

    @JvmStatic
    fun getApplication(): Application? = if (applicationRef == null) null else applicationRef!!.get()

    @JvmStatic
    fun init(app: Application, onNextTopActivityListener: OnNextTopActivityListener? = null) {
        app.initTopActivity(onNextTopActivityListener)
    }

}

fun Application.initTopActivity(onNextTopActivityListener: OnNextTopActivityListener? = null) {
    TopActivity.applicationRef = WeakReference(this)
    TopActivity.onNextTopActivityListener =
            if (onNextTopActivityListener == null) null
            else WeakReference(onNextTopActivityListener)
    registerActivityLifecycleCallbacks(LifecycleHandler())
}


fun activity() = TopActivity.get()
fun context(): Context? = activity()
fun app(): Application? = TopActivity.getApplication()
